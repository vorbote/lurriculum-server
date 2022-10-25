package cn.vorbote.curriculum.controllers;

import cn.vorbote.curriculum.enums.Region;
import cn.vorbote.curriculum.models.RegionModel;
import cn.vorbote.web.model.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 用于系统基础数据的控制器，主要向前端提供系统数据。<br>
 * Created at 2022/10/9 21:45
 *
 * @author vorbote
 */
@RestController
@Slf4j
@RequestMapping("/app")
public final class AppController {

    private List<Region> blockedRegions;

    @Autowired
    public void setBlockedRegions(List<Region> blockedRegions) {
        this.blockedRegions = blockedRegions;
    }

    /**
     * 获取所有的地区集合
     */
    @GetMapping("/regions")
    public ResponseResult<List<RegionModel>> getRegions() {
        return ResponseResult.success(Region.regionModels());
    }

    /**
     * 获取所有被封禁的地区
     */
    @GetMapping("/blocked-regions")
    public ResponseResult<List<RegionModel>> getBlockedRegions() {
        var blockedRegionsModel = new ArrayList<RegionModel>();
        blockedRegions.forEach(
                (item) -> blockedRegionsModel.add(RegionModel.fromRegion(item)));
        return ResponseResult.success(blockedRegionsModel);
    }

}
