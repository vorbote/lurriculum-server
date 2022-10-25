package cn.vorbote.curriculum.plains;

import cn.vorbote.curriculum.enums.Region;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName(autoResultMap = true)
public final class Teacher extends BasePo {

    /**
     * 教师ID
     */
    @TableId(type = IdType.AUTO)
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
    private Region region;

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

    @Override
    public Teacher setArchived(Integer archived) {
        this.archived = archived;
        return this;
    }

    @Override
    public Teacher setCreateAt(Long createAt) {
        this.createAt = createAt;
        return this;
    }
}
