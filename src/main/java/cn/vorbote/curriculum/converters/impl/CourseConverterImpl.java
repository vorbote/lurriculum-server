package cn.vorbote.curriculum.converters.impl;

import cn.vorbote.curriculum.converters.CourseConverter;
import cn.vorbote.curriculum.plains.Course;
import cn.vorbote.curriculum.transfers.CourseDto;
import cn.vorbote.curriculum.views.CourseVo;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * CourseConverterImpl<br>
 * Created at 2022/9/26 00:10
 *
 * @author vorbote
 */
@Component
public final class CourseConverterImpl implements CourseConverter {

    @Override
    public Course ordinary(CourseDto courseDto) {
        if (courseDto == null) {
            return null;
        }

        var course = new Course();
        course.setId(Optional.of(courseDto).map(CourseDto::getId).orElse(null));
        course.setName(Optional.of(courseDto).map(CourseDto::getName).orElse(null));
        course.setCredit(Optional.of(courseDto).map(CourseDto::getCredit).orElse(null));
        course.setTermId(Optional.of(courseDto).map(CourseDto::getTermId).orElse(null));

        return course;
    }

    @Override
    public CourseVo view(Course course) {
        if (course == null) {
            return null;
        }

        var courseVo = new CourseVo();
        courseVo.setId(Optional.of(course).map(Course::getId).orElse(null));
        courseVo.setName(Optional.of(course).map(Course::getName).orElse(null));
        courseVo.setCredit(Optional.of(course).map(Course::getCredit).orElse(null));
        courseVo.setTermId(Optional.of(course).map(Course::getTermId).orElse(null));

        return courseVo;
    }
}
