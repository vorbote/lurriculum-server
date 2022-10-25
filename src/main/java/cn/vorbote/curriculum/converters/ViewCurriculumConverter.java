package cn.vorbote.curriculum.converters;

import cn.vorbote.curriculum.plains.Curriculum;
import cn.vorbote.curriculum.plains.ViewCurriculum;
import cn.vorbote.curriculum.transfers.CurriculumDto;
import cn.vorbote.curriculum.transfers.ViewCurriculumDto;
import cn.vorbote.curriculum.views.CurriculumVo;
import cn.vorbote.curriculum.views.ViewCurriculumVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * CurriculumConverter<br>
 * Created at 21/09/2022 12:45
 *
 * @author theod
 */
@Mapper(componentModel = "spring")
public interface ViewCurriculumConverter
        extends BaseConverter<ViewCurriculumDto, ViewCurriculum, ViewCurriculumVo> {

    @Mappings({
            @Mapping(target = "startDate",
                    expression = "java(java.util.Optional.ofNullable(dataTransfer.getStartDate()).map((startDate) -> new cn.vorbote.core.time.DateTime(startDate, true)).orElse(null))"),
            @Mapping(target = "endDate",
                    expression = "java(java.util.Optional.ofNullable(dataTransfer.getEndDate()).map((endDate) -> new cn.vorbote.core.time.DateTime(endDate, true)).orElse(null))"),
    })
    @Override
    ViewCurriculum ordinary(ViewCurriculumDto dataTransfer);

    @Mappings({
            @Mapping(target = "startDate", expression = "java(plain.getStartDate().java())"),
            @Mapping(target = "endDate", expression = "java(plain.getEndDate().java())")
    })
    @Override
    ViewCurriculumVo view(ViewCurriculum plain);
}
