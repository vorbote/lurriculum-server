package cn.vorbote.curriculum.converters;

import cn.vorbote.curriculum.plains.Curriculum;
import cn.vorbote.curriculum.transfers.CurriculumDto;
import cn.vorbote.curriculum.views.CurriculumVo;

/**
 * CurriculumConverter<br>
 * Created at 21/09/2022 12:45
 *
 * @author theod
 */
public interface CurriculumConverter
        extends BaseConverter<CurriculumDto, Curriculum, CurriculumVo> {

    /**
     * 将 DTO 转换为 PO
     *
     * @param curriculumDto DTO 对象
     * @return PO 对象
     */
    @Override
    Curriculum ordinary(CurriculumDto curriculumDto);

    /**
     * 将 PO 转换为 VO
     *
     * @param curriculum PO 对象
     * @return VO 对象
     */
    @Override
    CurriculumVo view(Curriculum curriculum);
}
