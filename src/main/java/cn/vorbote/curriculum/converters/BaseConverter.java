package cn.vorbote.curriculum.converters;

import cn.vorbote.curriculum.plains.BasePo;
import cn.vorbote.curriculum.transfers.BaseDto;
import cn.vorbote.curriculum.views.BaseVo;

/**
 * 基础转换器，用于使其他转换器标准化<br>
 * Created at 20/09/2022 17:31
 *
 * @author theod
 */
public interface BaseConverter<D extends BaseDto, P extends BasePo, V extends BaseVo> {

    /**
     * 将 DTO 转换为 PO
     *
     * @param dataTransfer DTO 对象
     * @return PO 对象
     */
    P ordinary(D dataTransfer);

    /**
     * 将 PO 转换为 VO
     *
     * @param plain PO 对象
     * @return VO 对象
     */
    V view(P plain);

}
