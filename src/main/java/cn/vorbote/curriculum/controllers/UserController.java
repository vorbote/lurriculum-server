package cn.vorbote.curriculum.controllers;

import cn.vorbote.core.constants.Hash;
import cn.vorbote.core.time.DateTime;
import cn.vorbote.core.time.TimeSpan;
import cn.vorbote.core.utils.*;
import cn.vorbote.curriculum.converters.*;
import cn.vorbote.curriculum.enums.Region;
import cn.vorbote.curriculum.generators.RandomCodeGenerator;
import cn.vorbote.curriculum.plains.User;
import cn.vorbote.curriculum.plains.ViewCurriculum;
import cn.vorbote.curriculum.requests.RegisterRequest;
import cn.vorbote.curriculum.requests.VerifyPhoneRequest;
import cn.vorbote.curriculum.services.*;
import cn.vorbote.curriculum.transfers.UserDto;
import cn.vorbote.curriculum.utils.RedisUtil;
import cn.vorbote.curriculum.values.AppConstant;
import cn.vorbote.curriculum.values.Flag;
import cn.vorbote.curriculum.values.MessageSenderConstant;
import cn.vorbote.curriculum.values.TencentMessengerConstants;
import cn.vorbote.curriculum.views.UserVo;
import cn.vorbote.ical.Event;
import cn.vorbote.message.model.MessageRequest;
import cn.vorbote.message.sender.BasicSender;
import cn.vorbote.simplejwt.AccessKeyUtil;
import cn.vorbote.web.model.ResponseResult;
import cn.vorbote.web.utils.BizAssert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * UserController<br>
 * Created at 20/09/2022 17:21
 *
 * @author theod
 */
@Slf4j
@RestController
@RequestMapping("/user")
public final class UserController {

    private final UserService userService;

    private final ViewCurriculumService viewCurriculumService;

    private final UserConverter userConverter;

    private final AccessKeyUtil accessKeyUtil;

    private final SnowFlake snowFlake;

    private final BasicSender tencentSender;

    private final RandomCodeGenerator randomCodeGenerator;

    private final RedisUtil redisUtil;

    public UserController(UserService userService,
                          UserConverter userConverter,
                          AccessKeyUtil accessKeyUtil,
                          SnowFlake snowFlake,
                          ViewCurriculumService viewCurriculumService,
                          BasicSender tencentSender,
                          RandomCodeGenerator randomCodeGenerator,
                          RedisUtil redisUtil) {
        this.userService = userService;
        this.userConverter = userConverter;
        this.accessKeyUtil = accessKeyUtil;
        this.snowFlake = snowFlake;
        this.viewCurriculumService = viewCurriculumService;
        this.tencentSender = tencentSender;
        this.randomCodeGenerator = randomCodeGenerator;
        this.redisUtil = redisUtil;
    }

    private List<Region> blockedRegions;

    @Autowired
    public void setBlockedRegions(List<Region> blockedRegions) {
        if (CollectionUtil.isNotEmpty(blockedRegions)) {
            log.warn("电话服务被封锁的国家或地区：{}", blockedRegions);
        }
        this.blockedRegions = blockedRegions;
    }

    private void checkUsernameAndPassword(UserDto userDto) {
        BizAssert.notNull(userDto, "用户信息不能为空！");
        BizAssert.hasText(userDto.getUsername(), "用户名不能为空！");
        BizAssert.hasText(userDto.getPassword(), "密码不能为空！");
    }

    // region 账户相关

    @PostMapping("/login-by-phone")
    public ResponseResult<UserVo> loginByPhone(HttpServletResponse response,
                                               @RequestBody UserDto userDto) throws Exception {
        // checkUsernameAndPassword(userDto);
        BizAssert.isTrue(userDto.getRegion() != 0, "你电话号码是哪个国家或地区的呀？");
        BizAssert.hasText(userDto.getPhone(), "你电话号码没告诉我没法登录呀～");
        BizAssert.hasText(userDto.getPassword(), "密码是不是忘记填了？");

        // log.info("userDto: {}", userDto);

        var user = userService.lambdaQuery()
                .eq(User::getRegion, userDto.getRegion())
                .eq(User::getPhone, userDto.getPhone())
                .eq(User::getPassword, HashUtil.encrypt(Hash.MD5, userDto.getPassword()))
                .one();
        if (user == null) {
            return ResponseResult.unauthorized("用户名密码不正确呀宝贝儿~");
        }

        var authorization = accessKeyUtil.createTokenWithBean(TimeSpan.builder().minutes(30).build(),
                AppConstant.APP_NAME, new String[]{user.getUsername()}, user);
        response.setHeader(AppConstant.TOKEN_KEY, authorization);

        return ResponseResult.success(userConverter.view(user), "Hello 呀~");
    }

    @PostMapping("/login-by-email")
    public ResponseResult<UserVo> loginByEmail(HttpServletResponse response,
                                               @RequestBody UserDto userDto) throws Exception {
        // checkUsernameAndPassword(userDto);
        BizAssert.hasText(userDto.getEmail(), "你没告诉我的你的邮箱地址呀～");
        BizAssert.hasText(userDto.getPassword(), "你密码是啥？");

        // log.info("userDto: {}", userDto);

        var user = userService.lambdaQuery()
                .eq(User::getEmail, userDto.getEmail())
                .eq(User::getPassword, HashUtil.encrypt(Hash.MD5, userDto.getPassword()))
                .one();
        if (user == null) {
            return ResponseResult.unauthorized("用户名密码不正确呀宝贝儿~");
        }

        var authorization = accessKeyUtil.createTokenWithBean(TimeSpan.builder().minutes(30).build(),
                AppConstant.APP_NAME, new String[]{user.getUsername()}, user);
        response.setHeader(AppConstant.TOKEN_KEY, authorization);

        return ResponseResult.success(userConverter.view(user), "Hello 呀~");
    }

    @PostMapping("/login")
    public ResponseResult<UserVo> login(HttpServletResponse response,
                                        @RequestBody UserDto userDto) throws Exception {
        checkUsernameAndPassword(userDto);

        var user = userService.lambdaQuery()
                .eq(User::getUsername, userDto.getUsername())
                .eq(User::getPassword, HashUtil.encrypt(Hash.MD5, userDto.getPassword()))
                .one();
        if (user == null) {
            return ResponseResult.unauthorized("用户名密码不正确呀宝贝儿~");
        }

        var authorization = accessKeyUtil.createTokenWithBean(TimeSpan.builder().minutes(30).build(),
                AppConstant.APP_NAME, new String[]{user.getUsername()}, user);
        response.setHeader(AppConstant.TOKEN_KEY, authorization);

        return ResponseResult.success(userConverter.view(user), "Hello 呀~");
    }

    @PostMapping("/register")
    public ResponseResult<?> register(@RequestBody RegisterRequest request) {
        var userDto = request.user();
        checkUsernameAndPassword(userDto);
        BizAssert.notNull(userDto.getRegion(), "");
        BizAssert.hasText(userDto.getPhone(), "用户电话号码不能为空！");
        BizAssert.isTrue(!userDto.getUsername().matches("\\d*"), "用户名不能是纯数字！");
        BizAssert.isTrue(!userDto.getUsername().contains("@"), "用户名不能包含@符号！");

        if (userService.lambdaQuery().eq(User::getUsername, userDto.getUsername()).count() > 0) {
            return ResponseResult.forbidden("该用户名已被使用，你是不是已经注册过了？");
        }

        if (userService.lambdaQuery().eq(User::getPhone, userDto.getPhone()).count() > 0) {
            return ResponseResult.forbidden("该电话号码已被使用，你是不是把密码忘了？");
        }

        if (StringUtil.hasText(userDto.getEmail()) &&
                userService.lambdaQuery().eq(User::getEmail, userDto.getEmail()).count() > 0) {
            return ResponseResult.forbidden("该电子邮箱已被使用，你是不是把密码忘了？");
        }

        var validationCode = redisUtil.get(request.user().getPhone() + request.requestCode(), String.class);
        if (!request.validationCode().equalsIgnoreCase(validationCode)) {
            return ResponseResult.error("您的验证码输入错误！");
        }

        return BranchUtil
                .<ResponseResult<?>>anyMatch(
                        () -> userService.save(userConverter.ordinary(userDto)
                                .setId(snowFlake.nextId())
                                .setArchived(Flag.NOT_ARCHIVED)
                                .setCreateAt(DateTime.now().unix())))
                .handle(() -> ResponseResult.success("注册成功！"),
                        () -> ResponseResult.error("注册失败，请稍后重试或联系开发者！"));
    }

    // endregion

    @GetMapping(value = "/my-calendar", produces = "text/calendar; charset=UTF-8")
    public String getCalendar(@RequestParam("key") Long userId) {
        log.info("key = {}", userId);

        var calendar = new cn.vorbote.ical.Calendar()
                .setProductName("Curriculum Helper Version 0.0.1")
                .setCompanyName("Theodore Hills")
                .setName("课程表")
                .setDomainName("curriculum.vorbote.cn");

        var curriculums = viewCurriculumService.list(Wrappers.<ViewCurriculum>lambdaQuery()
                .eq(ViewCurriculum::getOwnerId, userId));
        curriculums.forEach((item) -> {
            var startDate = item.getStartDate();

            item.getWeeks().forEach((week) -> {
                var daysToAdd = (week - 1) * 7 + item.getDay();
                var tempStartTime = new DateTime(startDate.getTimestamp())
                        .addDays(daysToAdd)
                        .addSeconds(Math.toIntExact(item.getStart()));
                var tempEndTime = new DateTime(startDate.getTimestamp())
                        .addDays(daysToAdd)
                        .addSeconds(Math.toIntExact(item.getEnd()));

                calendar.addEvent(new Event()
                        .setSummary(String.format("%s - by %s",
                                item.getCourseName(), item.getTeacherName()))
                        .setStart(tempStartTime)
                        .setEnd(tempEndTime)
                        .setLocation(item.getLocation())
                        .setUid(snowFlake.nextId()));
            });
        });

        return calendar.resolve();
    }

    @GetMapping("/verify-phone")
    public ResponseResult<?> verifyPhone(VerifyPhoneRequest request) throws JsonProcessingException {
        // log.info("request: {}", request);
        // 验证手机号是否已经注册
        var count = userService.lambdaQuery()
                .eq(User::getPhone, request.phone())
                .count();
        BizAssert.isTrue(count == 0, "该手机号已被注册，请更换手机后重试！");

        // 生成验证码
        var validationCode = randomCodeGenerator.randomDigitCode(6);

        // 发送验证码
        var msgResp = tencentSender.send(new MessageRequest(MessageSenderConstant.SIGN,
                MessageSenderConstant.TEMPLATE_REGISTER_ZH_HANS,
                new String[]{validationCode, request.requestCode()},
                String.format("%d%s", request.region(), request.phone())));
        // log.info(msgResp.code());
        if (TencentMessengerConstants.OK.equalsIgnoreCase(msgResp.code())) {
            var key = request.phone() + request.requestCode();
            redisUtil.set(key, validationCode);
            return ResponseResult.success("短信发送成功！");
        } else {
            return ResponseResult.error(msgResp.message());
        }
    }

}
