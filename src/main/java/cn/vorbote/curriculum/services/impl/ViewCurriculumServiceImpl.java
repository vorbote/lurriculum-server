package cn.vorbote.curriculum.services.impl;

import cn.vorbote.curriculum.mappers.CurriculumMapper;
import cn.vorbote.curriculum.mappers.ViewCurriculumMapper;
import cn.vorbote.curriculum.plains.Curriculum;
import cn.vorbote.curriculum.plains.ViewCurriculum;
import cn.vorbote.curriculum.services.CurriculumService;
import cn.vorbote.curriculum.services.ViewCurriculumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * CurriculumServiceImpl<br>
 * Created at 21/09/2022 13:27
 *
 * @author theod
 */
@Service
public class ViewCurriculumServiceImpl
        extends ServiceImpl<ViewCurriculumMapper, ViewCurriculum>
        implements ViewCurriculumService {
}
