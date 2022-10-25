package cn.vorbote.curriculum.services.impl;

import cn.vorbote.curriculum.mappers.TermMapper;
import cn.vorbote.curriculum.plains.Term;
import cn.vorbote.curriculum.services.TermService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * TermServiceImpl<br>
 * Created at 21/09/2022 13:30
 *
 * @author theod
 */
@Service
public class TermServiceImpl extends ServiceImpl<TermMapper, Term> implements TermService {
}
