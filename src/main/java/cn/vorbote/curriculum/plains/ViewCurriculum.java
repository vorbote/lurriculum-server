package cn.vorbote.curriculum.plains;

import cn.vorbote.core.time.DateTime;
import cn.vorbote.curriculum.handlers.DateTypeHandler;
import cn.vorbote.curriculum.handlers.WeeksTypeHandler;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * ViewCurrirulum<br>
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
@TableName(autoResultMap = true)
public final class ViewCurriculum extends BasePo {

    @TableId
    private Long id;

    private Integer day;

    @TableField(jdbcType = JdbcType.VARCHAR, typeHandler = WeeksTypeHandler.class)
    private List<Integer> weeks;

    private Long start;

    private Long end;

    private String location;

    private Long courseId;

    private String courseName;

    private Long teacherId;

    private String teacherName;

    private Long termId;

    @TableField(jdbcType = JdbcType.BIGINT, typeHandler = DateTypeHandler.class)
    private DateTime startDate;

    @TableField(jdbcType = JdbcType.BIGINT, typeHandler = DateTypeHandler.class)
    private DateTime endDate;

    private Long ownerId;

    @Override
    public ViewCurriculum setArchived(Integer archived) {
        this.archived = archived;
        return this;
    }

    @Override
    public ViewCurriculum setCreateAt(Long createAt) {
        this.createAt = createAt;
        return this;
    }
}
