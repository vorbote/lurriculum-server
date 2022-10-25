package cn.vorbote.curriculum.controllers;

import cn.vorbote.core.exceptions.NotImplementedException;
import cn.vorbote.core.time.DateTime;
import cn.vorbote.core.utils.BranchUtil;
import cn.vorbote.core.utils.SnowFlake;
import cn.vorbote.curriculum.converters.CurriculumConverter;
import cn.vorbote.curriculum.converters.ViewCurriculumConverter;
import cn.vorbote.curriculum.plains.ViewCurriculum;
import cn.vorbote.curriculum.requests.BatchCurriculumRequest;
import cn.vorbote.curriculum.services.CurriculumService;
import cn.vorbote.curriculum.services.ViewCurriculumService;
import cn.vorbote.curriculum.transfers.CurriculumDto;
import cn.vorbote.curriculum.views.CurriculumVo;
import cn.vorbote.curriculum.views.ViewCurriculumVo;
import cn.vorbote.web.constants.WebStatus;
import cn.vorbote.web.model.ResponseResult;
import cn.vorbote.web.utils.BizAssert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CurriculumController<br>
 * Created at 26/09/2022 19:59
 *
 * @author theod
 */
@RestController
@RequestMapping("/user/curriculum")
public final class CurriculumController {

    private final CurriculumService curriculumService;

    private final ViewCurriculumService viewCurriculumService;

    private final CurriculumConverter curriculumConverter;

    private final ViewCurriculumConverter viewCurriculumConverter;

    private final SnowFlake curriculumFlake;

    @Autowired
    public CurriculumController(CurriculumService curriculumService,
                                ViewCurriculumService viewCurriculumService,
                                CurriculumConverter curriculumConverter,
                                ViewCurriculumConverter viewCurriculumConverter,
                                SnowFlake curriculumFlake) {
        this.curriculumService = curriculumService;
        this.viewCurriculumService = viewCurriculumService;
        this.curriculumConverter = curriculumConverter;
        this.viewCurriculumConverter = viewCurriculumConverter;
        this.curriculumFlake = curriculumFlake;
    }

    // region 课程表相关
    @PostMapping("/")
    public ResponseResult<?> addCurriculum(@RequestBody CurriculumDto curriculumDto) {
        BizAssert.notNull(curriculumDto.getCourseId(), "课程ID不能为空！");
        BizAssert.notNull(curriculumDto.getDay(), "课程上课日期不能为空！");
        BizAssert.notNull(curriculumDto.getStart(), "课程开始时间不能为空！");
        BizAssert.notNull(curriculumDto.getEnd(), "课程结束时间不能为空！");
        BizAssert.isTrue(curriculumDto.getStart() < curriculumDto.getEnd(),
                "课程开始时间不能在结束时间之后！");
        BizAssert.notNull(curriculumDto.getWeeks(), "课程上课周次不能为空！");
        BizAssert.notNull(curriculumDto.getTeacherId(), "不能没有老师上课！");

        curriculumDto.setId(curriculumFlake.nextId());

        return BranchUtil
                .<ResponseResult<?>>allMatch(() ->
                        curriculumService.save(
                                curriculumConverter.ordinary(curriculumDto)
                                        .setId(curriculumFlake.nextId())
                                        .setCreateAt(DateTime.now().unix())))
                .handle(() -> ResponseResult.success("插入成功！"),
                        () -> ResponseResult.error("插入失败！"));
    }

    @PostMapping("/batch/{key}")
    public ResponseResult<?> addCurriculums(@PathVariable Long key,
                                            @RequestBody BatchCurriculumRequest batchCurriculumRequest) {
        throw new NotImplementedException("该功能暂未实现！");
    }

    @GetMapping("/{courseId}/list")
    public ResponseResult<IPage<ViewCurriculumVo>> selectCurriculums(@RequestParam(defaultValue = "1") Integer index,
                                                                     @RequestParam(defaultValue = "10") Integer size,
                                                                     @PathVariable Long courseId) {
        var results = viewCurriculumService.lambdaQuery()
                .eq(ViewCurriculum::getCourseId, courseId)
                .page(new Page<>(index, size))
                .convert(viewCurriculumConverter::view);
        return ResponseResult.success(results)
                .code(BranchUtil.<Integer>allMatch(() -> results.getTotal() != 0)
                        .handle(WebStatus.NO_CONTENT::getCode, WebStatus.OK::getCode));
    }

    @GetMapping("/{id}")
    public ResponseResult<CurriculumVo> selectCurriculum(@PathVariable Long id) {
        return null;
    }

    @PutMapping("/")
    public ResponseResult<?> updateCurriculum(@RequestBody CurriculumDto curriculumDto) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseResult<?> deleteCurriculum(@PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/batch")
    public ResponseResult<?> deleteCurriculums(@RequestBody List<Long> ids) {
        throw new NotImplementedException("该功能暂未实现，详情请咨询开发者 <zihlu.wang@outlook.com>");
    }

    // endregion

}
