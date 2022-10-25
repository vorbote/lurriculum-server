package cn.vorbote.curriculum.converters.impl;

import cn.vorbote.curriculum.converters.CurriculumConverter;
import cn.vorbote.curriculum.plains.Course;
import cn.vorbote.curriculum.plains.Curriculum;
import cn.vorbote.curriculum.transfers.CourseDto;
import cn.vorbote.curriculum.transfers.CurriculumDto;
import cn.vorbote.curriculum.views.CurriculumVo;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * CurriculumConverterImpl<br>
 * Created at 2022/9/26 00:16
 *
 * @author vorbote
 */
@Component
public final class CurriculumConverterImpl implements CurriculumConverter {

    @Override
    public Curriculum ordinary(CurriculumDto curriculumDto) {
        if (curriculumDto == null) {
            return null;
        }

        var curriculum = new Curriculum();
        curriculum.setId(Optional.of(curriculumDto).map(CurriculumDto::getId).orElse(null));
        curriculum.setDay(Optional.of(curriculumDto).map(CurriculumDto::getDay).orElse(null));
        curriculum.setWeeks(Optional.of(curriculumDto).map(CurriculumDto::getWeeks).orElse(null));
        curriculum.setStart(Optional.of(curriculumDto).map(CurriculumDto::getStart).orElse(null));
        curriculum.setEnd(Optional.of(curriculumDto).map(CurriculumDto::getEnd).orElse(null));
        curriculum.setLocation(Optional.of(curriculumDto).map(CurriculumDto::getLocation).orElse(null));
        curriculum.setCourseId(Optional.of(curriculumDto).map(CurriculumDto::getCourseId).orElse(null));
        curriculum.setTeacherId(Optional.of(curriculumDto).map(CurriculumDto::getTeacherId).orElse(null));

        return curriculum;
    }

    @Override
    public CurriculumVo view(Curriculum curriculum) {
        if (curriculum == null) {
            return null;
        }

        var curriculumVo = new CurriculumVo();
        curriculumVo.setId(Optional.of(curriculum).map(Curriculum::getId).orElse(null));
        curriculumVo.setDay(Optional.of(curriculum).map(Curriculum::getDay).orElse(null));
        curriculumVo.setWeeks(Optional.of(curriculum).map(Curriculum::getWeeks).orElse(null));
        curriculumVo.setStart(Optional.of(curriculum).map(Curriculum::getStart).orElse(null));
        curriculumVo.setEnd(Optional.of(curriculum).map(Curriculum::getEnd).orElse(null));
        curriculumVo.setLocation(Optional.of(curriculum).map(Curriculum::getLocation).orElse(null));
        curriculumVo.setCourseId(Optional.of(curriculum).map(Curriculum::getCourseId).orElse(null));
        curriculumVo.setTeacherId(Optional.of(curriculum).map(Curriculum::getTeacherId).orElse(null));

        return curriculumVo;
    }
}
