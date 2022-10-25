package cn.vorbote.curriculum.converters;

import cn.vorbote.curriculum.plains.Term;
import cn.vorbote.curriculum.transfers.TermDto;
import cn.vorbote.curriculum.views.TermVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * TermConverter<br>
 * Created at 21/09/2022 12:45
 *
 * @author theod
 */
@Mapper(componentModel = "spring")
public interface TermConverter
        extends BaseConverter<TermDto, Term, TermVo> {

    /**
     * 将 DTO 转换为 PO
     *
     * @param termDto DTO 对象
     * @return PO 对象
     */
    @Mappings({
            @Mapping(target = "startDate",
                    expression = "java(java.util.Optional.ofNullable(termDto.getStartDate()).map((startDate) -> new cn.vorbote.core.time.DateTime(startDate, true)).orElse(null))"),
            @Mapping(target = "endDate",
                    expression = "java(java.util.Optional.ofNullable(termDto.getEndDate()).map((endDate) -> new cn.vorbote.core.time.DateTime(endDate, true)).orElse(null))"),
            @Mapping(target = "archived", ignore = true),
            @Mapping(target = "createAt", ignore = true)
    })
    @Override
    Term ordinary(TermDto termDto);

    /**
     * 将 PO 转换为 VO
     *
     * @param term PO 对象
     * @return VO 对象
     */
    @Mappings({
            @Mapping(target = "startDate", expression = "java(term.getStartDate().java())"),
            @Mapping(target = "endDate", expression = "java(term.getEndDate().java())")
    })
    @Override
    TermVo view(Term term);
}
