package cn.vorbote.curriculum.views;

import cn.vorbote.curriculum.enums.Region;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * UserVo<br>
 * Created at 20/09/2022 17:34
 *
 * @author theod
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public final class UserVo extends BaseVo {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 地区
     */
    private String region;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;

}
