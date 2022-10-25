package cn.vorbote.curriculum.views;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * Course<br>
 * Created at 20/09/2022 20:20
 *
 * @author theod
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public final class CourseVo extends BaseVo {

    /**
     * 课程ID
     */
    private Long id;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 学分
     */
    private Integer credit;

    /**
     * 学期ID
     */
    private Long termId;

}
