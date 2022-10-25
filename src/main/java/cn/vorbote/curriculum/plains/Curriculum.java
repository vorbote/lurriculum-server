package cn.vorbote.curriculum.plains;

import cn.vorbote.curriculum.handlers.WeeksTypeHandler;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;

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
@TableName(autoResultMap = true)
public final class Curriculum extends BasePo {

    /**
     * 课程表ID
     */
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 星期几
     */
    private Integer day;

    /**
     * 上课周数
     */
    // 格式 1,2,3,4
    @TableField(jdbcType = JdbcType.VARCHAR, typeHandler = WeeksTypeHandler.class)
    private List<Integer> weeks;

    /**
     * 开始时间 (直接以秒算)
     */
    private Long start;

    /**
     * 结束时间 (直接以秒算)
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

    /**
     * 教师ID
     */
    private Long teacherId;

    @Override
    public Curriculum setArchived(Integer archived) {
        this.archived = archived;
        return this;
    }

    @Override
    public Curriculum setCreateAt(Long createAt) {
        this.createAt = createAt;
        return this;
    }
}
