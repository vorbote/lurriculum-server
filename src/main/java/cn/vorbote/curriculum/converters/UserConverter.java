package cn.vorbote.curriculum.converters;

import cn.vorbote.curriculum.plains.User;
import cn.vorbote.curriculum.transfers.UserDto;
import cn.vorbote.curriculum.views.UserVo;

/**
 * UserConverter<br>
 * Created at 20/09/2022 17:29
 *
 * @author theod
 */
public interface UserConverter extends BaseConverter<UserDto, User, UserVo> {

    /**
     * 将 DTO 转换为 PO
     *
     * @param userDto DTO 对象
     * @return PO 对象
     */
    @Override
    User ordinary(UserDto userDto);

    /**
     * 将 PO 转换为 VO
     *
     * @param user PO 对象
     * @return VO 对象
     */
    @Override
    UserVo view(User user);
}
