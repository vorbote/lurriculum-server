package cn.vorbote.curriculum.plains;

import cn.vorbote.core.time.DateTime;
import cn.vorbote.curriculum.handlers.DateTypeHandler;
import cn.vorbote.web.utils.BizAssert;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;

import java.util.Calendar;

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
@TableName(autoResultMap = true)
public final class Term extends BasePo {

    /**
     * 学期ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    /**
     * 创建人ID
     */
    private Long ownerId;

    /**
     * 学期开始日期
     */
    @TableField(jdbcType = JdbcType.BIGINT, typeHandler = DateTypeHandler.class)
    private DateTime startDate;

    /**
     * 学期结束日期
     */
    @TableField(jdbcType = JdbcType.BIGINT, typeHandler = DateTypeHandler.class)
    private DateTime endDate;

    @Override
    public Term setArchived(Integer archived) {
        this.archived = archived;
        return this;
    }

    @Override
    public Term setCreateAt(Long createAt) {
        this.createAt = createAt;
        return this;
    }
}
