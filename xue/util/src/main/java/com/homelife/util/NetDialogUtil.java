package com.homelife.util;

import android.app.Activity;
import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * @Description 防内存泄露的网络请求进度条
 * @Author Xue
 * @CreateDate 2017/9/19
 */
public class NetDialogUtil {
    private WeakHashMap<Context, WeakReference<MyDialog>> mWeakHashMap;
    private WeakHashMap<Context, Integer> mWeakCounterHashMap;
    private static NetDialogUtil mInstance = new NetDialogUtil();

    public static NetDialogUtil getInstance() {
        return mInstance;
    }

    private NetDialogUtil() {
        mWeakCounterHashMap = new WeakHashMap<>();
        mWeakHashMap = new WeakHashMap<>();
    }

    /**
     * @Description 加载框
     * @Author Xue
     * @CreateDate
     */
    public void showDialog(WeakReference<Context> weakRef, String msg) {
        if (weakRef != null) {
            Context context = weakRef.get();
            ObjectUtil.checkNonNull(context, "showDialog context == null");
            Activity activity = (Activity) context; //外部对context是否关闭已经做了判断 参考HttpUtil.showLoading()
            if (!activity.isFinishing()) { //activity 没有关闭出现请求进度条
                if (mWeakHashMap == null) {
                    mWeakHashMap = new WeakHashMap<>();
                }
                if (mWeakCounterHashMap == null) {
                    mWeakCounterHashMap = new WeakHashMap<>();
                }
                WeakReference<MyDialog> mWeakRef = mWeakHashMap.get(context);
                MyDialog progressDialog = null;
                if (mWeakRef != null && mWeakRef.get() != null) {
                    progressDialog = mWeakRef.get();
                } else {
                    msg = StringUtil.isNotBlank(msg) ? msg : activity.getString(R.string.core_dialog);
                    progressDialog = new MyDialog(activity, msg);
                    progressDialog.setOnDismissListener((mDialogInterface) -> { //清空
                        mWeakCounterHashMap.remove(weakRef.get());
                        mWeakHashMap.remove(weakRef.get());
                    });
                    mWeakRef = new WeakReference<MyDialog>(progressDialog);
                    mWeakHashMap.put(context, mWeakRef);
                }
                Integer counter = mWeakCounterHashMap.get(context);
                if (counter == null) {
                    mWeakCounterHashMap.put(context, 1);
                } else {
                    counter += 1;
                    mWeakCounterHashMap.put(context, counter);
                }
                if (progressDialog != null && !progressDialog.isShowing()) {
                    progressDialog.setCanceledOnTouchOutside(false); //默认不能点击边缘取消对话框，可以传递值进行控制
                    progressDialog.show();
                }
            }
        }
    }

    /**
     * @Description 对外暴露的请求接口
     * @Author Xue
     * @CreateDate
     */
    public void showDialog(Context mContext) {
        WeakReference<Context> wRef = new WeakReference<Context>(mContext);
        showDialog(wRef, R.string.core_dialog);
    }

    /**
     * @Description 重载
     * @Author Xue
     * @CreateDate
     */
    public void showDialog(WeakReference<Context> wRef, int resId) {
        if (wRef != null) {
            Context context = wRef.get();
            ObjectUtil.checkNonNull(context, "showDialog context == null");
            String msg = context.getResources().getString(resId);
            showDialog(wRef, msg);
        }
    }

    /**
     * @Description 重载
     * @Author Xue
     * @CreateDate
     */
    public void showDialog(Context mContext, int resId) {
        WeakReference<Context> wRef = new WeakReference<Context>(mContext);
        showDialog(wRef, resId);
    }

    /**
     * @Description 重载
     * @Author Xue
     * @CreateDate
     */
    public void showDialog(Context mContext, String msg) {
        if (StringUtil.isBlank(msg)) {
            showDialog(mContext);
        } else {
            WeakReference<Context> wRef = new WeakReference<Context>(mContext);
            showDialog(wRef, msg);
        }
    }

    /**
     * @Description 取消对话框
     * @Author Xue
     * @CreateDate
     */
    public void cancleDialog(Context mContext) {
        WeakReference<Context> wRef = new WeakReference<Context>(mContext);
        cancleDialog(wRef);
    }

    /**
     * @Description 取消显示框
     * @Author Xue
     * @CreateDate
     */
    public void cancleDialog(WeakReference<Context> weakRef) {
        if (mWeakHashMap != null && weakRef != null) {
            Context context = weakRef.get();
            ObjectUtil.checkNonNull(context, "cancleDialog context == null");
            WeakReference<MyDialog> mWeakRef = mWeakHashMap.get(context);
            if(mWeakRef == null){
                return;
            }
            ObjectUtil.checkNonNull(mWeakRef, "cancleDialog mWeakRef == null");
            MyDialog progressDialog = mWeakRef.get();
            if (progressDialog != null && progressDialog.isShowing()) {
                if (mWeakCounterHashMap != null) {
                    Integer counter = mWeakCounterHashMap.get(context);
                    if (counter != null) {
                        counter -= 1;
                        if (counter <= 0) { //关闭所有Dialog
                            mWeakCounterHashMap.remove(context);
                            mWeakHashMap.remove(context);
                            progressDialog.setOnDismissListener(null);
                            progressDialog.dismiss();
                        } else {
                            mWeakCounterHashMap.put(context, counter);
                        }
                    } else {
                        progressDialog.dismiss();
                    }
                } else {
                    progressDialog.dismiss();
                }
            }
        }
    }

}
