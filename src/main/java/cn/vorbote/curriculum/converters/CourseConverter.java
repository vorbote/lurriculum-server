package cn.vorbote.curriculum.converters;

import cn.vorbote.curriculum.plains.Course;
import cn.vorbote.curriculum.plains.User;
import cn.vorbote.curriculum.transfers.CourseDto;
import cn.vorbote.curriculum.transfers.UserDto;
import cn.vorbote.curriculum.views.CourseVo;
import cn.vorbote.curriculum.views.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.Mapping;

/**
 * UserConverter<br>
 * Created at 20/09/2022 17:29
 *
 * @author theod
 */
public interface CourseConverter extends BaseConverter<CourseDto, Course, CourseVo> {

    /**
     * 将 DTO 转换为 PO
     *
     * @param courseDto DTO 对象
     * @return PO 对象
     */
    @Override
    Course ordinary(CourseDto courseDto);

    /**
     * 将 PO 转换为 VO
     *
     * @param course PO 对象
     * @return VO 对象
     */
    @Override
    CourseVo view(Course course);
}
