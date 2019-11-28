package com.homelife.base.base;

import java.util.List;

/**
 * @Description 权限申请封装回调
 * @Author Xue
 * @CreateDate 2017/9/20
 */
public interface PermissionListener {
    void onGranted();//已授权

    void onDenied(List<String> deniedPermission);//未授权
}
