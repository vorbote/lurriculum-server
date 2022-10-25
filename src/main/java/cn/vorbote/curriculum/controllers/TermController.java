package cn.vorbote.curriculum.controllers;

import cn.vorbote.core.exceptions.NotImplementedException;
import cn.vorbote.core.time.DateTime;
import cn.vorbote.core.utils.BranchUtil;
import cn.vorbote.core.utils.SnowFlake;
import cn.vorbote.curriculum.converters.TermConverter;
import cn.vorbote.curriculum.plains.Course;
import cn.vorbote.curriculum.plains.Term;
import cn.vorbote.curriculum.plains.User;
import cn.vorbote.curriculum.services.CourseService;
import cn.vorbote.curriculum.services.TermService;
import cn.vorbote.curriculum.transfers.TermDto;
import cn.vorbote.curriculum.views.TermVo;
import cn.vorbote.simplejwt.AccessKeyUtil;
import cn.vorbote.web.constants.WebStatus;
import cn.vorbote.web.model.ResponseResult;
import cn.vorbote.web.utils.BizAssert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

/**
 * TermController<br>
 * Created at 26/09/2022 19:56
 *
 * @author theod
 */
@Slf4j
@RestController
@RequestMapping("/user/term")
public final class TermController {

    private final SnowFlake snowFlake;

    private final TermService termService;

    private final TermConverter termConverter;

    private final AccessKeyUtil accessKeyUtil;

    private final CourseService courseService;

    @Autowired
    public TermController(TermService termService,
                          TermConverter termConverter,
                          SnowFlake snowFlake,
                          AccessKeyUtil accessKeyUtil,
                          CourseService courseService
    ) {
        this.termService = termService;
        this.termConverter = termConverter;
        this.snowFlake = snowFlake;
        this.accessKeyUtil = accessKeyUtil;
        this.courseService = courseService;
    }

    // region 学期相关

    @PostMapping("/")
    public ResponseResult<?> addTerm(@RequestHeader String authorization,
                                     @RequestBody TermDto termDto) throws Exception {
        var currentUser = accessKeyUtil.getBean(authorization, User.class);

        var term = termConverter.ordinary(termDto)
                .setOwnerId(currentUser.getId())
                .setCreateAt(DateTime.now().unix());

        BizAssert.isTrue(term.getStartDate().toCalendar().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY,
                "学期开始日期必须是周日！");
        BizAssert.isTrue(term.getEndDate().toCalendar().get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY,
                "学期结束日期必须是周六！");

        // 同一个人创建的学期不能同名
        if (termService.lambdaQuery()
                .eq(Term::getOwnerId, term.getOwnerId())
                .eq(Term::getName, term.getName())
                .count() >= 1) {
            return ResponseResult.error("学期名称已经存在了，麻烦您换一个名字再试试！")
                    .code(WebStatus.BAD_REQUEST.getCode());
        }

        return BranchUtil
                .<ResponseResult<?>>allMatch(
                        () -> termService.save(term))
                .handle(() -> ResponseResult.success("学期创建成功！"),
                        () -> ResponseResult.error("学期创建失败！"));
    }

    @GetMapping("/list")
    public ResponseResult<IPage<TermVo>> selectTerms(@RequestHeader String authorization,
                                                     @RequestParam(defaultValue = "1") Integer index,
                                                     @RequestParam(defaultValue = "10") Integer size) throws Exception {
        var currentUser = accessKeyUtil.getBean(authorization, User.class);

        var terms = termService.lambdaQuery()
                .eq(Term::getOwnerId, currentUser.getId())
                .page(new Page<>(index, size))
                .convert(termConverter::view);
        return ResponseResult.success(terms, "查询成功！")
                .code(BranchUtil
                        .<Integer>allMatch(terms.getRecords().size() != 0)
                        .handle(WebStatus.OK::getCode, WebStatus.NO_CONTENT::getCode)
                );
    }

    @GetMapping("/{id}")
    public ResponseResult<TermVo> selectTerm(@RequestHeader String authorization,
                                             @PathVariable Long id) throws Exception {
        var currentUser = accessKeyUtil.getBean(authorization, User.class);

        var term = termConverter.view(termService.lambdaQuery()
                .eq(Term::getOwnerId, currentUser.getId())
                .eq(Term::getId, id)
                .one());

        return ResponseResult.success(term, "信息查询成功！")
                .code(BranchUtil
                        .<Integer>allMatch(term != null)
                        .handle(WebStatus.OK::getCode, WebStatus.NO_CONTENT::getCode)
                );
    }

    @PutMapping("/")
    public ResponseResult<?> updateTerm(@RequestHeader String authorization,
                                        @RequestBody TermDto termDto) throws Exception {
        var currentUser = accessKeyUtil.getBean(authorization, User.class);

        return BranchUtil.<ResponseResult<?>>allMatch(
                        () -> termService.lambdaUpdate()
                                .eq(Term::getId, termDto.getId())
                                .eq(Term::getOwnerId, currentUser.getId())
                                .update(termConverter.ordinary(termDto)))
                .handle(() -> ResponseResult.success("更新成功！"),
                        () -> ResponseResult.error("更新失败！"));
    }

    @DeleteMapping("/{id}")
    public ResponseResult<?> deleteTerm(@RequestHeader String authorization,
                                        @PathVariable Long id) throws Exception {
        var currentUser = accessKeyUtil.getBean(authorization, User.class);

        var count = courseService.lambdaQuery()
                .eq(Course::getTermId, id)
                .count();
        BizAssert.isTrue(count == 0, "还有本学期的课程没有删除，请先删除课程后再删除学期！");

        return BranchUtil
                .<ResponseResult<?>>allMatch(
                        () -> termService.remove(
                                Wrappers.<Term>lambdaQuery()
                                        .eq(Term::getId, id)
                                        .eq(Term::getOwnerId, currentUser.getId())))
                .handle(() -> ResponseResult.success("删除成功！"),
                        () -> ResponseResult.error("删除失败！"));
    }

    @DeleteMapping("/batch")
    public ResponseResult<?> deleteTerms(@RequestHeader String authorization,
                                         @RequestBody List<Long> ids) {
        throw new NotImplementedException("该功能暂未实现！");
    }

    // endregion

}
