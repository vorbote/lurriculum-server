package cn.vorbote.curriculum.views;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * ViewCurriculumVo<br>
 * Created at 2022/9/30 09:09
 *
 * @author vorbote
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public final class ViewCurriculumVo extends BaseVo {

    private Long id;

    private Integer day;

    private List<Integer> weeks;

    private Long start;

    private Long end;

    private String location;

    private Long courseId;

    private String courseName;

    private Long teacherId;

    private String teacherName;

    private Long termId;

    private Long startDate;

    private Long endDate;

    private Long ownerId;

}
