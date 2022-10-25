package cn.vorbote.curriculum.plains;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * BaseEntity<br>
 * Created at 20/09/2022 17:24
 *
 * @author theod
 */
public abstract sealed class BasePo permits Course, Curriculum, Teacher, Term, User, ViewCurriculum {

    @TableLogic
    protected Integer archived;

    protected Long createAt;

    public Integer getArchived() {
        return archived;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public abstract BasePo setArchived(Integer archived);

    public abstract BasePo setCreateAt(Long createAt);
}
