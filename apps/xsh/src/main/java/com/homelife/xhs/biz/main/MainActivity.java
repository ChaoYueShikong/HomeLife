package com.homelife.xhs.biz.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.homelife.base.anno.AfterViews;
import com.homelife.base.anno.RootLayout;
import com.homelife.base.base.EventClass;
import com.homelife.base.receiver.NetEvent;
import com.homelife.xhs.BaseBizActivity;
import com.homelife.xhs.R;
import com.homelife.util.StatusBarUtil;

import butterknife.BindView;

/**
 * @Description 主页
 * @Author Xue
 * @CreateDate
 */
@RootLayout(R.layout.act_main)
public class MainActivity extends BaseBizActivity {

    @BindView(R.id.view)
    View mView; //所有视图的处理

    private MainPresenter mPresenter;
    private MainView mMainView;


    @AfterViews
    void onCreate() {
        mPresenter = new MainPresenter(this);
        mMainView = new MainView(this, mView, mPresenter);
        mPresenter.attachView(mMainView);
        mPresenter.subscribe();
        setExit(true);//设置双击退出
        //可以做一些初始化公共接口的调用
//        ComDataCenter.INSTANCE.getInfo();

        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, findViewById(R.id.pull_load_recycler_view));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mMainView != null) {
            mMainView.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mMainView != null) {
            mMainView.stop();
        }
    }


    @Override
    public void onEventOnUI(EventClass even) { //子类继承
        if (even instanceof NetEvent) {

        }
    }


    private static final int ACTION_REQUEST_PERMISSIONS = 0x001;
    /**
     * 所需的所有权限信息
     */
    private static final String[] NEEDED_PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };



    private boolean checkPermissions(String[] neededPermissions) {
        if (neededPermissions == null || neededPermissions.length == 0) {
            return true;
        }
        boolean allGranted = true;
        for (String neededPermission : neededPermissions) {
            allGranted &= ContextCompat.checkSelfPermission(this, neededPermission) == PackageManager.PERMISSION_GRANTED;
        }
        return allGranted;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ACTION_REQUEST_PERMISSIONS) {
            boolean isAllGranted = true;
            for (int grantResult : grantResults) {
                isAllGranted &= (grantResult == PackageManager.PERMISSION_GRANTED);
            }
            if (isAllGranted) {
                //业务
            } else {
                Toast.makeText(this, R.string.permission_str, Toast.LENGTH_SHORT).show();
            }
        }
    }


}
