package cn.vorbote.curriculum.converters.impl;

import cn.vorbote.core.constants.Hash;
import cn.vorbote.core.utils.HashUtil;
import cn.vorbote.curriculum.converters.UserConverter;
import cn.vorbote.curriculum.enums.Region;
import cn.vorbote.curriculum.plains.User;
import cn.vorbote.curriculum.transfers.UserDto;
import cn.vorbote.curriculum.views.UserVo;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * UserConverterImpl<br>
 * Created at 2022/9/26 10:02
 *
 * @author vorbote
 */
@Component
public final class UserConverterImpl implements UserConverter {
    @Override
    public User ordinary(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        var user = new User();
        user.setId(Optional.of(userDto)
                .map(UserDto::getId).orElse(null));
        user.setUsername(Optional.of(userDto)
                .map(UserDto::getUsername).orElse(null));
        user.setPassword(Optional.of(userDto)
                .map(item -> HashUtil.encrypt(Hash.MD5, item.getPassword())).orElse(null));
        user.setRegion(Optional.of(userDto)
                .map(item -> Region.byCode(item.getRegion())).orElse(null));
        user.setPhone(Optional.of(userDto)
                .map(UserDto::getPhone).orElse(null));
        user.setEmail(Optional.of(userDto)
                .map(UserDto::getEmail).orElse(null));

        return user;
    }

    @Override
    public UserVo view(User user) {
        if (user == null) {
            return null;
        }

        var userVo = new UserVo();
        userVo.setId(Optional.of(user).map(User::getId).orElse(null));
        userVo.setUsername(Optional.of(user).map(User::getUsername).orElse(null));
        userVo.setRegion(Optional.of(user).map(User::getRegion).map(Region::getLabel).orElse(null));
        userVo.setPhone(Optional.of(user).map(User::getPhone).orElse(null));
        userVo.setEmail(Optional.of(user).map(User::getEmail).orElse(null));

        return userVo;
    }
}
