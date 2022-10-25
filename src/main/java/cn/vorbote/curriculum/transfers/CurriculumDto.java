package cn.vorbote.curriculum.transfers;

import cn.vorbote.curriculum.plains.BasePo;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Curriculum<br>
 * Created at 20/09/2022 20:22
 *
 * @author theod
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public final class CurriculumDto extends BaseDto {

    /**
     * 课程表ID
     */
    private Long id;

    /**
     * 星期几
     */
    private Integer day;

    /**
     * 上课周数
     */
    // 格式 1,2,3,4
    private List<Integer> weeks;

    /**
     * 开始时间
     */
    private Long start;

    /**
     * 结束时间
     */
    private Long end;

    /**
     * 上课地点
     */
    private String location;

    /**
     * 课程ID
     */
    private Long courseId;

    private Long teacherId;

}
