package com.homelife.util;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;


/**
 * @Description 时间工具
 * @Author Xue
 * @CreateDate
 */
public final class TimeUtil {
    private TimeUtil() {
        throw new IllegalStateException("No instance!");
    }

    public static int checkDuration(String name, long duration, TimeUnit unit) {
        if (duration < 0) throw new IllegalArgumentException(name + " < 0");
        if (unit == null) throw new NullPointerException("unit == null");
        long millis = unit.toMillis(duration);
        if (millis > Integer.MAX_VALUE)
            throw new IllegalArgumentException(name + " too large.");
        if (millis == 0 && duration > 0)
            throw new IllegalArgumentException(name + " too small.");
        return (int) millis;
    }

    /**
     * @Description 获取当前时间
     * @Author Xue
     * @CreateDate
     */
    public static String getTime(String type) {
        String str = "2018年08月27日";
        Calendar cal = Calendar.getInstance();
        int y = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH) + 1;
        int d = cal.get(Calendar.DATE);
        int h = cal.get(Calendar.HOUR_OF_DAY);
        int mi = cal.get(Calendar.MINUTE);
        int s = cal.get(Calendar.SECOND);
        //System.out.println("现在时刻是" + y + "年" + m + "月" + d + "日" + h + "时" + mi + "分" + s + "秒");
        if ("date".equals(type)) {
            str = y + "年" + m + "月" + d + "日";
        } else if ("time".equals(type)) {
            String tempY = h + "";
            String tempM = mi + "";
            String tempS = s + "";
            if (h < 10) {
                tempY = "0" + h;
            }
            if (mi < 10) {
                tempM = "0" + mi;
            }
            if (s < 10) {
                tempS = "0" + s;
            }
            str = tempY + ":" + tempM + ":" + tempS;
        } else {
            str = y + "年" + m + "月" + d + "日" + h + "时" + mi + "分" + s + "秒";
        }
        return str;
    }
}
