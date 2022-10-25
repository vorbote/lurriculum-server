package cn.vorbote.curriculum.transfers;

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
public final class ViewCurriculumDto extends BaseDto {

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
