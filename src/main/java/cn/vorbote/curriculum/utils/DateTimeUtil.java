package cn.vorbote.curriculum.utils;

import cn.vorbote.core.time.DateTime;
import cn.vorbote.web.utils.BizAssert;

/**
 * DateTimeUtil<br>
 * Created at 2022/10/12 09:16
 *
 * @author vorbote
 */
public final class DateTimeUtil {

    public static DateTime transform(Long value) {
        var s = String.valueOf(value);
        BizAssert.isTrue(s.matches("\\d{8}"), "日期格式不正确！");
        var year = Integer.parseInt(s.substring(0, 4));
        var month = Integer.parseInt(s.substring(4, 6));
        var date = Integer.parseInt(s.substring(6, 8));

        return new DateTime(year, month, date);
    }

    public static DateTime transform(String s) {
        BizAssert.isTrue(s.matches("\\d{8}"), "日期格式不正确！");
        var year = Integer.parseInt(s.substring(0, 4));
        var month = Integer.parseInt(s.substring(4, 6));
        var date = Integer.parseInt(s.substring(6, 8));

        return new DateTime(year, month, date);
    }

}
