package cn.vorbote.curriculum.models;

import cn.vorbote.curriculum.enums.Region;

/**
 * RegionModel<br>
 * Created at 2022/10/9 21:58
 *
 * @author vorbote
 */
public record RegionModel(String label, Integer code) {

    public static RegionModel fromRegion(Region region) {
        return new RegionModel(region.getLabel(), region.getCode());
    }

}
