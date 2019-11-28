package com.homelife.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

/**
 * @Description 自定义转动Dialog
 * @Author Xue
 * @CreateDate
 */
public class MyDialog extends Dialog {
    public MyDialog(Context context) {
        this(context, R.style.CustomProgressDialog, "");
    }

    public MyDialog(Context context, String strMessage) {
        this(context, R.style.CustomProgressDialog, strMessage);
    }

    public MyDialog(Context context, int theme, String strMessage) {
        super(context, theme);
        this.setContentView(R.layout.core_loading_dialog);
        this.getWindow().getAttributes().gravity = Gravity.CENTER;
        TextView tvMsg = (TextView) this.findViewById(R.id.tv_core_dailog);
        if (tvMsg != null) {
            tvMsg.setText(strMessage);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus) {
            dismiss();
        }
    }
}
