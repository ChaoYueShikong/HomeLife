package com.homelife.base.view.badgeview;

import android.content.Context;
import android.view.Gravity;


/**
 * @Description Badge工厂创建红点和数字
 * @Author Xue
 * @CreateDate 2017/10/13
 */
public class BadgeFactory {
    /**
     * @Description 生成红点
     * @Author Xue
     * @CreateDate
     */
    public static BadgeViewNew createDot(Context context) {
        return new BadgeViewNew(context).setWidthAndHeight(6, 6).setSpace(1,1).setTextSize(0).setBadgeGravity(Gravity.RIGHT | Gravity.TOP).setShape(BadgeViewNew.SHAPE_CIRCLE);
    }

    /**
     * @Description 创建圆形
     * @Author Xue
     * @CreateDate
     */
    public static BadgeViewNew createCircle(Context context) {
        return new BadgeViewNew(context).setWidthAndHeight(14, 14).setTextSize(7).setBadgeGravity(Gravity.RIGHT | Gravity.TOP).setShape(BadgeViewNew.SHAPE_CIRCLE);
    }

    /**
     * @Description 创建矩形
     * @Author Xue
     * @CreateDate
     */
    public static BadgeViewNew createRectangle(Context context) {
        return new BadgeViewNew(context).setWidthAndHeight(25, 20).setTextSize(12).setBadgeGravity(Gravity.RIGHT | Gravity.TOP).setShape(BadgeViewNew.SHAPE_RECTANGLE);
    }

    /**
     * @Description 创建椭圆
     * @Author Xue
     * @CreateDate
     */
    public static BadgeViewNew createOval(Context context) {
        return new BadgeViewNew(context).setWidthAndHeight(25, 20).setTextSize(12).setBadgeGravity(Gravity.RIGHT | Gravity.TOP).setShape(BadgeViewNew.SHAPE_OVAL);
    }

    /**
     * @Description 创建正方形
     * @Author Xue
     * @CreateDate
     */
    public static BadgeViewNew createSquare(Context context) {
        return new BadgeViewNew(context).setWidthAndHeight(20, 20).setTextSize(12).setBadgeGravity(Gravity.RIGHT | Gravity.TOP).setShape(BadgeViewNew.SHAPE_SQUARE);
    }

    /**
     * @Description 创建圆角矩形
     * @Author Xue
     * @CreateDate
     */
    public static BadgeViewNew createRoundRect(Context context) {
        return new BadgeViewNew(context).setWidthAndHeight(25, 20).setTextSize(12).setBadgeGravity(Gravity.RIGHT | Gravity.TOP).setShape(BadgeViewNew.SHAPTE_ROUND_RECTANGLE);
    }

    /**
     * @Description 默认形状圆形
     * @Author Xue
     * @CreateDate
     */
    public static BadgeViewNew create(Context context) {
        return new BadgeViewNew(context);
    }

}
