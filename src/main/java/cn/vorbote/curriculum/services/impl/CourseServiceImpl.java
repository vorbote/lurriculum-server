package cn.vorbote.curriculum.services.impl;

import cn.vorbote.curriculum.mappers.CourseMapper;
import cn.vorbote.curriculum.plains.Course;
import cn.vorbote.curriculum.services.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * CourseServiceImpl<br>
 * Created at 21/09/2022 13:26
 *
 * @author theod
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
}
