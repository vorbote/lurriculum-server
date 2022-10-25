package cn.vorbote.curriculum.views;

import cn.vorbote.curriculum.enums.Region;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * Teacher<br>
 * Created at 20/09/2022 20:15
 *
 * @author theod
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public final class TeacherVo extends BaseVo {

    /**
     * 教师ID
     */
    private Long id;

    /**
     * 职称
     */
    private String title;

    /**
     * 教师姓名
     */
    private String name;

    /**
     * 部门
     */
    private String department;

    /**
     * 工作单位
     */
    private String workplace;

    /**
     * 教师地区
     */
    private String region;

    /**
     * 教师手机号
     */
    private String phone;

    /**
     * 教师电子邮箱
     */
    private String email;

    /**
     * 创建人ID
     */
    private Long ownerId;

}
