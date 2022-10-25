package cn.vorbote.curriculum.services;

import cn.vorbote.curriculum.enums.Region;
import cn.vorbote.curriculum.plains.User;
import cn.vorbote.web.model.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * UserService<br>
 * Created at 21/09/2022 13:30
 *
 * @author theod
 */
public interface UserService extends IService<User> {

    User login(User user);
}
