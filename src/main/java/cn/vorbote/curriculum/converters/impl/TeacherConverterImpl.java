package cn.vorbote.curriculum.converters.impl;

import cn.vorbote.curriculum.converters.TeacherConverter;
import cn.vorbote.curriculum.enums.Region;
import cn.vorbote.curriculum.plains.Teacher;
import cn.vorbote.curriculum.transfers.TeacherDto;
import cn.vorbote.curriculum.views.TeacherVo;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * TeacherConverterImpl<br>
 * Created at 2022/9/26 10:01
 *
 * @author vorbote
 */
@Component
public final class TeacherConverterImpl implements TeacherConverter {
    @Override
    public Teacher ordinary(TeacherDto teacherDto) {
        if (teacherDto == null) {
            return null;
        }

        var teacher = new Teacher();
        teacher.setId(Optional.of(teacherDto).map(TeacherDto::getId).orElse(null));
        teacher.setTitle(Optional.of(teacherDto).map(TeacherDto::getTitle).orElse(null));
        teacher.setName(Optional.of(teacherDto).map(TeacherDto::getName).orElse(null));
        teacher.setDepartment(Optional.of(teacherDto).map(TeacherDto::getDepartment).orElse(null));
        teacher.setWorkplace(Optional.of(teacherDto).map(TeacherDto::getWorkplace).orElse(null));
        teacher.setRegion(Optional.of(teacherDto).map((item) -> Region.byCode(item.getRegion())).orElse(null));
        teacher.setPhone(Optional.of(teacherDto).map(TeacherDto::getPhone).orElse(null));
        teacher.setEmail(Optional.of(teacherDto).map(TeacherDto::getEmail).orElse(null));
        teacher.setOwnerId(Optional.of(teacherDto).map(TeacherDto::getOwnerId).orElse(null));

        return teacher;
    }

    @Override
    public TeacherVo view(Teacher teacher) {
        if (teacher == null) {
            return null;
        }

        var teacherVo = new TeacherVo();
        teacherVo.setId(Optional.of(teacher).map(Teacher::getId).orElse(null));
        teacherVo.setTitle(Optional.of(teacher).map(Teacher::getTitle).orElse(null));
        teacherVo.setName(Optional.of(teacher).map(Teacher::getName).orElse(null));
        teacherVo.setDepartment(Optional.of(teacher).map(Teacher::getDepartment).orElse(null));
        teacherVo.setWorkplace(Optional.of(teacher).map(Teacher::getWorkplace).orElse(null));
        teacherVo.setRegion(Optional.of(teacher).map(Teacher::getRegion).map(Region::getLabel).orElse(null));
        teacherVo.setPhone(Optional.of(teacher).map(Teacher::getPhone).orElse(null));
        teacherVo.setEmail(Optional.of(teacher).map(Teacher::getEmail).orElse(null));
        teacherVo.setOwnerId(Optional.of(teacher).map(Teacher::getOwnerId).orElse(null));

        return teacherVo;
    }
}
