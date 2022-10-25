package cn.vorbote.curriculum.plains;

import cn.vorbote.curriculum.enums.Region;
import cn.vorbote.simplejwt.annotations.JwtIgnore;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * User<br>
 * Created at 20/09/2022 17:10
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
public final class User extends BasePo {

    /**
     * 用户ID
     */
    @TableId
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JwtIgnore
    private String password;

    /**
     * 地区
     */
    @JwtIgnore
    private Region region;

    /**
     * 电话号码
     */
    @JwtIgnore
    private String phone;

    /**
     * 电子邮箱
     */
    @JwtIgnore
    private String email;

    @Override
    public User setCreateAt(Long createAt) {
        this.createAt = createAt;
        return this;
    }

    @Override
    public User setArchived(Integer archived) {
        this.archived = archived;
        return this;
    }

}
