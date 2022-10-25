package cn.vorbote.curriculum.controllers;

import cn.vorbote.core.exceptions.NotImplementedException;
import cn.vorbote.core.time.DateTime;
import cn.vorbote.core.utils.BranchUtil;
import cn.vorbote.core.utils.SnowFlake;
import cn.vorbote.curriculum.converters.TeacherConverter;
import cn.vorbote.curriculum.plains.Teacher;
import cn.vorbote.curriculum.plains.User;
import cn.vorbote.curriculum.services.TeacherService;
import cn.vorbote.curriculum.transfers.TeacherDto;
import cn.vorbote.curriculum.views.TeacherVo;
import cn.vorbote.simplejwt.AccessKeyUtil;
import cn.vorbote.web.model.ResponseResult;
import cn.vorbote.web.utils.BizAssert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TeacherController<br>
 * Created at 26/09/2022 19:38
 *
 * @author theod
 */
@RestController
@RequestMapping("/user/teacher")
public final class TeacherController {

    private final AccessKeyUtil accessKeyUtil;

    private final TeacherService teacherService;

    private final TeacherConverter teacherConverter;

    private final SnowFlake snowFlake;

    @Autowired
    public TeacherController(AccessKeyUtil accessKeyUtil,
                             TeacherService teacherService,
                             TeacherConverter teacherConverter,
                             SnowFlake snowFlake
    ) {
        this.accessKeyUtil = accessKeyUtil;
        this.teacherService = teacherService;
        this.teacherConverter = teacherConverter;
        this.snowFlake = snowFlake;
    }

    // region 教师相关
    @PostMapping("/")
    public ResponseResult<?> addTeacher(@RequestHeader String authorization,
                                        @RequestBody TeacherDto teacherDto)
            throws Exception {
        var currentUser = accessKeyUtil.getBean(authorization, User.class);

        BizAssert.notNull(teacherDto, "教师信息不能为空！");
        BizAssert.hasText(teacherDto.getName(), "教师姓名不能为空！");

        return BranchUtil
                .<ResponseResult<?>>allMatch(() -> teacherService.save(
                        teacherConverter.ordinary(teacherDto)
                                .setOwnerId(currentUser.getId())
                                .setCreateAt(DateTime.now().unix())))
                .handle(() -> ResponseResult.success("教师添加成功！"),
                        () -> ResponseResult.error("教师添加失败！"));
    }

    @GetMapping("/list")
    public ResponseResult<IPage<TeacherVo>> selectTeachers(@RequestHeader String authorization,
                                                           @RequestParam(defaultValue = "1") Integer index,
                                                           @RequestParam(defaultValue = "10") Integer size) throws Exception {
        var currentUser = accessKeyUtil.getBean(authorization, User.class);

        BizAssert.isTrue(index > 0, "查询页面不能小于1！");
        BizAssert.isTrue(size > 0, "查询大小不能小于1！");

        var teachers = teacherService.lambdaQuery()
                .eq(Teacher::getOwnerId, currentUser.getId())
                .page(new Page<>(index, size))
                .convert(teacherConverter::view);

        return ResponseResult.success(teachers, "查询成功！");
    }

    @GetMapping("/{id}")
    public ResponseResult<TeacherVo> selectTeacher(@RequestHeader String authorization,
                                                   @PathVariable Long id) throws Exception {
        var currentUser = accessKeyUtil.getBean(authorization, User.class);
        var teacher = teacherConverter.view(teacherService.lambdaQuery()
                .eq(Teacher::getId, id)
                .eq(Teacher::getOwnerId, currentUser.getId())
                .one());

        return ResponseResult.success(teacher, "查询成功！");
    }

    @PutMapping("/")
    public ResponseResult<?> updateTeacher(@RequestHeader String authorization,
                                           @RequestBody TeacherDto teacherDto) throws Exception {
        var currentUser = accessKeyUtil.getBean(authorization, User.class);

        return BranchUtil
                .<ResponseResult<?>>allMatch(teacherService.lambdaQuery()
                        .eq(Teacher::getId, teacherDto.getId())
                        .eq(Teacher::getOwnerId, currentUser.getId())
                        .count() == 0)
                .handle(() -> ResponseResult.error("这不是你的老师，别乱改！"),
                        () -> BranchUtil
                                .<ResponseResult<?>>allMatch(
                                        () -> teacherService.lambdaUpdate()
                                                .eq(Teacher::getId, teacherDto.getId())
                                                .eq(Teacher::getOwnerId, currentUser.getId())
                                                .update(teacherConverter.ordinary(teacherDto)))
                                .handle(() -> ResponseResult.success("更新成功！"),
                                        () -> ResponseResult.error("更新失败！")));
    }

    @DeleteMapping("/{id}")
    public ResponseResult<?> deleteTeacher(@RequestHeader String authorization,
                                           @PathVariable Long id) throws Exception {
        var currentUser = accessKeyUtil.getBean(authorization, User.class);

        return BranchUtil
                .<ResponseResult<?>>allMatch(teacherService.lambdaQuery()
                        .eq(Teacher::getId, id)
                        .eq(Teacher::getOwnerId, currentUser.getId())
                        .count() == 0)
                .handle(() -> ResponseResult.error("这不是你的老师，别乱改！"),
                        () -> BranchUtil
                                .<ResponseResult<?>>allMatch(
                                        () -> teacherService.remove(Wrappers.<Teacher>lambdaQuery()
                                                .eq(Teacher::getId, id)))
                                .handle(() -> ResponseResult.success("删除成功！"),
                                        () -> ResponseResult.error("删除失败！")));
    }

    @DeleteMapping("/batch")
    public ResponseResult<?> deleteTeachers(@RequestHeader String authorization,
                                            @RequestBody List<Long> ids) throws Exception {
        throw new NotImplementedException("该功能暂未实现，如有疑问请联系开发者 <zihlu.wang@outlook.com> ");
    }
    // endregion

}
