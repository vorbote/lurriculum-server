package cn.vorbote.curriculum.controllers;

import cn.vorbote.core.exceptions.NotImplementedException;
import cn.vorbote.core.time.DateTime;
import cn.vorbote.core.utils.BranchUtil;
import cn.vorbote.core.utils.SnowFlake;
import cn.vorbote.curriculum.converters.CourseConverter;
import cn.vorbote.curriculum.plains.Course;
import cn.vorbote.curriculum.plains.Curriculum;
import cn.vorbote.curriculum.plains.User;
import cn.vorbote.curriculum.services.CourseService;
import cn.vorbote.curriculum.services.CurriculumService;
import cn.vorbote.curriculum.transfers.CourseDto;
import cn.vorbote.curriculum.values.Flag;
import cn.vorbote.curriculum.views.CourseVo;
import cn.vorbote.simplejwt.AccessKeyUtil;
import cn.vorbote.web.model.ResponseResult;
import cn.vorbote.web.utils.BizAssert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CourseController<br>
 * Created at 26/09/2022 19:57
 *
 * @author theod
 */
@Slf4j
@RestController
@RequestMapping("/user/course")
public final class CourseController {

    private final CourseService courseService;

    private final CurriculumService curriculumService;

    private final CourseConverter courseConverter;

    private final AccessKeyUtil accessKeyUtil;

    private final SnowFlake snowFlake;

    @Autowired
    public CourseController(CourseService courseService,
                            CurriculumService curriculumService,
                            CourseConverter courseConverter,
                            AccessKeyUtil accessKeyUtil,
                            SnowFlake snowFlake) {
        this.courseService = courseService;
        this.curriculumService = curriculumService;
        this.courseConverter = courseConverter;
        this.accessKeyUtil = accessKeyUtil;
        this.snowFlake = snowFlake;
    }

    // region 课程相关

    @PostMapping("/")
    public ResponseResult<?> addCourse(@RequestHeader String authorization,
                                       @RequestBody CourseDto courseDto) throws Exception {
        BizAssert.notNull(courseDto, "课程信息不能为空！");
        BizAssert.notNull(courseDto.getTermId(), "学期ID不能为空！");

        var currentUser = accessKeyUtil.getBean(authorization, User.class);

        return BranchUtil.<ResponseResult<?>>allMatch(
                        () -> courseService.save(courseConverter.ordinary(courseDto)
                                .setArchived(Flag.NOT_ARCHIVED)
                                .setCreateAt(DateTime.now().unix())))
                .handle(() -> ResponseResult.success("添加课程成功！"),
                        () -> ResponseResult.error("添加课程失败！"));
    }

    @GetMapping("/list/{termId}")
    public ResponseResult<IPage<CourseVo>> selectCourse(@RequestHeader String authorization,
                                                        @RequestParam(defaultValue = "1") Integer index,
                                                        @RequestParam(defaultValue = "10") Integer size,
                                                        @PathVariable Long termId) throws Exception {
        var currentUser = accessKeyUtil.getBean(authorization, User.class);

        var courses = courseService.lambdaQuery()
                .eq(Course::getTermId, termId)
                .page(new Page<>(index, size))
                .convert(courseConverter::view);

        return ResponseResult.success(courses, "查询成功！");
    }

    @GetMapping("/{id}")
    public ResponseResult<CourseVo> selectCourse(@RequestHeader String authorization,
                                                 @PathVariable Long id) {
        var course = courseConverter.view(courseService.lambdaQuery()
                .eq(Course::getId, id)
                .one());
        return ResponseResult.success(course, "数据查询成功！");
    }

    @PutMapping("/")
    public ResponseResult<?> updateCourse(@RequestHeader String authorization,
                                          @RequestBody CourseDto courseDto) {
        return BranchUtil
                .<ResponseResult<?>>allMatch(
                        () -> courseService.lambdaUpdate()
                                .eq(Course::getId, courseDto.getId())
                                .update(courseConverter.ordinary(courseDto)))
                .handle(() -> ResponseResult.success("修改成功！"),
                        () -> ResponseResult.error("修改失败！"));
    }

    @DeleteMapping("/{id}")
    public ResponseResult<?> deleteCourse(@RequestHeader String authorization,
                                          @PathVariable Long id) {
        var count = curriculumService.lambdaQuery()
                .eq(Curriculum::getCourseId, id)
                .count();
        BizAssert.isTrue(count == 0, "该课程还有上课计划未删除，请先删除上课计划！");

        return BranchUtil
                .<ResponseResult<?>>allMatch(
                        () -> courseService.remove(Wrappers.<Course>lambdaQuery()
                                .eq(Course::getId, id)))
                .handle(() -> ResponseResult.success("删除成功！"),
                        () -> ResponseResult.error("删除失败！"));
    }

    @DeleteMapping("/batch")
    public ResponseResult<?> deleteCourses(@RequestHeader String authorization,
                                           @RequestBody List<Long> ids) {
        throw new NotImplementedException("该功能暂未实现！");
    }

    // endregion

}
