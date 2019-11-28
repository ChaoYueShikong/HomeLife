/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */
package com.homelife.util;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;


/**
 * @Description 对话框工具
 * @Author Xue
 * @CreateDate
 */
public class DialogUtil {

    /**
     * @description 系统对话框
     * @author Xue
     */
    public static void sysDialog(String msg, String title, Context context) {
        try {
            Builder b = new Builder(context).setTitle(title).setMessage(msg);
            b.setPositiveButton(Static.CONTEXT.getString(R.string.sure), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    dialog.cancel();
                }
            }).create();
            b.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @description 系统对话框
     * @author Xue
     */
    public static void sysDialog(String msg, Context context) {
        try {
            Builder b = new Builder(context).setTitle(Static.CONTEXT.getString(R.string.info)).setMessage(msg);
            b.setPositiveButton(Static.CONTEXT.getString(R.string.sure), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    dialog.cancel();
                }
            }).setNeutralButton(Static.CONTEXT.getString(R.string.cancle), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            }).create();
            b.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @description
     * @author Xue
     */
    public static void commonDialog(String msg, Context context) {
        try {
            Builder builer = new Builder(context);
            builer.setMessage(msg).setTitle(Static.CONTEXT.getString(R.string.info)).setPositiveButton(
                    Static.CONTEXT.getString(R.string.sure), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).create().show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @description 带回调接口的dialog
     * @author Xue
     */
    public static void dialog(Context context, String title, String msg, final DialogListener listener) {
        try {
            Builder b = new Builder(context).setTitle(title).setMessage(msg);
            b.setPositiveButton(Static.CONTEXT.getString(R.string.sure), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    listener.afterDilog();
                }
            }).setNeutralButton(Static.CONTEXT.getString(R.string.cancle), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            }).create();
            b.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @description
     * @author Xue
     * @createDate 2014年10月26日
     */
    public static void dialog(String msg, String title, String confirm, String cancle, Context context, final DialogListener listener) {
        try {
            Builder b = new Builder(context).setTitle(title).setMessage(msg);
            b.setPositiveButton(confirm, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    listener.afterDilog();
                }
            }).setNeutralButton(cancle, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            }).create();
            b.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @description listener.afterDilog();
     * @author Xue
     * @createDate 2014年10月26日
     */
    public static void dialog(String msg, String title, String confirm, String cancle, Context context,final DialogListener2 listener) {
        try {
            Builder b = new Builder(context).setTitle(title).setMessage(msg);
            b.setPositiveButton(confirm, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    listener.confirm();
                }
            }).setNeutralButton(cancle, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listener.cancel();
                }
            }).create();
            b.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @description
     * @author Xue
     */
    public static void dialog(String msg, Context context, final DialogListener listener) {
        try {
            Builder b = new Builder(context).setTitle(Static.CONTEXT.getString(R.string.info)).setMessage(msg);
            b.setPositiveButton(Static.CONTEXT.getString(R.string.sure), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    listener.afterDilog();
                }
            }).setNeutralButton(Static.CONTEXT.getString(R.string.cancle), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            }).create();
            b.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @description
     * @author Xue
     */
    public static void dialog(Activity context, String title, String msg, final DialogListener listener) {
        try {
            Builder b = new Builder(context).setTitle(title).setMessage(msg);
            b.setPositiveButton(Static.CONTEXT.getString(R.string.sure), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    listener.afterDilog();
                }
            }).setNeutralButton(Static.CONTEXT.getString(R.string.cancle), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            }).create();
            b.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @description 系统默认进度条
     * @author Xue
     */
    public static ProgressDialog showDialog(Context context, ProgressDialog progressDialog, String mMsg) {
        try {
            if (progressDialog == null) {
                mMsg = StringUtil.isNotBlank(mMsg) ? mMsg : Static.CONTEXT.getString(R.string.loading);
                progressDialog = ProgressDialog.show(context, "", mMsg); //"加载中，请稍后……"
            }
            if (context != null) {
                progressDialog.setCancelable(true);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return progressDialog;
    }


    /**
     * @description 菊花转动效果
     * @author Xue
     */
    public static CoreDialog getDialog(Context context, CoreDialog progressDialog) {
        if (context != null) {
            try {
                if (progressDialog == null) {
                    progressDialog = new CoreDialog(context);
                }
                progressDialog.setCancelable(true);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return progressDialog;
        } else {
            return null;
        }

    }


}
