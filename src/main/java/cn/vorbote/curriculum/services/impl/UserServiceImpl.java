package cn.vorbote.curriculum.services.impl;

import cn.vorbote.core.utils.CollectionUtil;
import cn.vorbote.curriculum.enums.Region;
import cn.vorbote.curriculum.generators.RandomCodeGenerator;
import cn.vorbote.curriculum.mappers.UserMapper;
import cn.vorbote.curriculum.plains.User;
import cn.vorbote.curriculum.services.UserService;
import cn.vorbote.curriculum.values.MessageSenderConstant;
import cn.vorbote.message.model.MessageRequest;
import cn.vorbote.message.sender.BasicSender;
import cn.vorbote.web.constants.WebStatus;
import cn.vorbote.web.model.ResponseResult;
import cn.vorbote.web.utils.BizAssert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * UserServiceImpl<br>
 * Created at 21/09/2022 13:31
 *
 * @author theod
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User login(User user) {
        return baseMapper.login(user);
    }

}
