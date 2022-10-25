package cn.vorbote.curriculum.mappers;

import cn.vorbote.curriculum.plains.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * UserMapper<br>
 * Created at 21/09/2022 12:56
 *
 * @author theod
 */
public interface UserMapper extends BaseMapper<User> {

    User login(User user);

}
