package cn.vorbote.curriculum.plains;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName(autoResultMap = true)
public final class Course extends BasePo {

    /**
     * 课程ID
     */
    @TableId(type = IdType.AUTO)
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

    @Override
    public Course setArchived(Integer archived) {
        this.archived = archived;
        return this;
    }

    @Override
    public Course setCreateAt(Long createAt) {
        this.createAt = createAt;
        return this;
    }
}
