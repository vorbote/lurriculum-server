package cn.vorbote.curriculum.converters;

import cn.vorbote.curriculum.plains.Teacher;
import cn.vorbote.curriculum.transfers.TeacherDto;
import cn.vorbote.curriculum.views.TeacherVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.Mapping;

/**
 * TeacherConverter<br>
 * Created at 21/09/2022 12:45
 *
 * @author theod
 */
public interface TeacherConverter
        extends BaseConverter<TeacherDto, Teacher, TeacherVo> {

    /**
     * 将 DTO 转换为 PO
     *
     * @param teacherDto DTO 对象
     * @return PO 对象
     */
    @Override
    Teacher ordinary(TeacherDto teacherDto);

    /**
     * 将 PO 转换为 VO
     *
     * @param teacher PO 对象
     * @return VO 对象
     */
    @Override
    TeacherVo view(Teacher teacher);
}
