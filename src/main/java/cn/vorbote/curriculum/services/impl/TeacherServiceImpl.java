package cn.vorbote.curriculum.services.impl;

import cn.vorbote.curriculum.mappers.TeacherMapper;
import cn.vorbote.curriculum.plains.Teacher;
import cn.vorbote.curriculum.services.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * TeacherServiceImpl<br>
 * Created at 21/09/2022 13:29
 *
 * @author theod
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
}
