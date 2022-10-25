package cn.vorbote.curriculum.transfers;

import cn.vorbote.curriculum.enums.Region;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * UserDto<br>
 * Created at 20/09/2022 17:36
 *
 * @author theod
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public final class UserDto extends BaseDto {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 地区
     */
    private Integer region;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;

}
