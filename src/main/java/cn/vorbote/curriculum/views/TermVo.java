package cn.vorbote.curriculum.views;

import cn.vorbote.core.time.DateTime;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 学期<br>
 * Created at 20/09/2022 20:25
 *
 * @author theod
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public final class TermVo extends BaseVo {

    /**
     * 学期ID
     */
    private Long id;

    private String name;

    /**
     * 创建人ID
     */
    private Long ownerId;

    /**
     * 学期开始日期
     */
    private Long startDate;

    /**
     * 学期结束日期
     */
    private Long endDate;

}
