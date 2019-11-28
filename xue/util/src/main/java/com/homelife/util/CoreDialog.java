/*
 * Copyright (c) 2016  Corporation. All rights reserved.
 *
 */

package com.homelife.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;


/**
 * @Description 默认菊花的转动
 * @Author Xue
 * @Version 1.0
 */
public class CoreDialog extends Dialog {
    public CoreDialog(Context context) {
        this(context, R.style.CustomProgressDialog, "");
    }

    public CoreDialog(Context context, String strMessage) {
        this(context, R.style.CustomProgressDialog, strMessage);
    }

    public CoreDialog(Context context, int theme, String strMessage) {
        super(context, theme);
        this.setContentView(R.layout.core_loading);
        this.getWindow().getAttributes().gravity = Gravity.CENTER;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus) {
            dismiss();
        }
    }
}
