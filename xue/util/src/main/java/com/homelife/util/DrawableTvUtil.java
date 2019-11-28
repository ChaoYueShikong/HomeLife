/*
 * Copyright (c) 2012, Xue Corporation, All Rights Reserved
 */
package com.homelife.util;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * @author Xue
 * @Description 根据方位在TextView上显示Drawable工具
 * @createDate
 */
public final class DrawableTvUtil {
    /**
     * @author Xue
     * @Description 方位
     * @createDate
     */
    public static enum Direction {
        LEFT, TOP, RIGHT, BOTTOM;
    }

    /**
     * @Description 根据方位在TextView上显示Drawable
     * @author Xue
     * @createDate
     */
    public static void setDrawable(TextView tv, Drawable mDrawable, Direction direction) {
        mDrawable.setBounds(0, 0, mDrawable.getMinimumWidth(), mDrawable.getMinimumHeight());
        switch (direction) {
            case LEFT:
                tv.setCompoundDrawables(mDrawable, null, null, null);
                break;
            case TOP:
                tv.setCompoundDrawables(null, mDrawable, null, null);
                break;
            case RIGHT:
                tv.setCompoundDrawables(null, null, mDrawable, null);
                break;
            case BOTTOM:
                tv.setCompoundDrawables(null, null, null, mDrawable);
                break;
            default:
                break;
        }
    }

    /**
     * @Description 根据方位在TextView上显示Drawable
     * @author Xue
     * @createDate
     */
    public static void setDrawable(TextView tv, int id, Direction direction) {
        Drawable mDrawable = Static.CONTEXT.getResources().getDrawable(id);//R.drawable.drawable
        mDrawable.setBounds(0, 0, mDrawable.getMinimumWidth(), mDrawable.getMinimumHeight());
        switch (direction) {
            case LEFT:
                tv.setCompoundDrawables(mDrawable, null, null, null);
                break;
            case TOP:
                tv.setCompoundDrawables(null, mDrawable, null, null);
                break;
            case RIGHT:
                tv.setCompoundDrawables(null, null, mDrawable, null);
                break;
            case BOTTOM:
                tv.setCompoundDrawables(null, null, null, mDrawable);
                break;
            default:
                break;
        }
    }
}
