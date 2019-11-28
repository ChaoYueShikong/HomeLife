/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */
package com.homelife.util;

import java.io.File;

/**
 * @Description 路径定义
 * @Author Xue
 * @CreateDate
 */
public final class PathUtil {
    public static final String PATH_PN = Static.CONTEXT.getPackageName(); //包名
    public static final String PATH_ROOT = File.separator + PATH_PN + File.separator; //  /<package name>/
    public static final String PATH_BASE = "/." + PATH_PN + File.separator; //  /.<package name>/
    public static final String PATH_ROOT_NOSD = Static.CONTEXT.getCacheDir().getPath() + File.separator; // 无SD卡缓存目录 /data/data/<package name>/cache

    public static final String CACHE_FILE = ".cache_file"; // 图片缓存目录名称
    public static final String CACHE_JSON = ".cache_json"; // JSONS数据缓存目录名称
    public static final String CACHE_LOCAL_PHOTO = ".cache_local_photo"; // 拍照预留缓存目录
    public static final String CACHE_LOCAL_VEDIO = ".cache_local_vedio"; // 通过自定义相机拍摄的视频和缩略图预留缓存目录
    public static final String CRASH = "crash"; // 奔溃错误日志
    public static final String CACHE_LOG = ".qxlog"; // 日志

    public static final String PATH_FILE = PATH_BASE + CACHE_FILE + File.separator; // /<package name>/cache_file/ 图片缓存文件
    public static final String PATH_JSON = PATH_BASE + CACHE_JSON + File.separator; // JSON缓存目录
    public static final String PATH_LOCAL_PHONE = PATH_BASE + CACHE_LOCAL_PHOTO + File.separator; //拍照图片目录
    public static final String PATH_CRASH = PATH_BASE + CRASH + File.separator; //crash错误日志PATH_ROOT
    public static final String PATH_CRASH_NOSD = PATH_BASE + CRASH + File.separator; //crash错误日志PATH_ROOT_NOSD
    public static final String PATH_LOCAL_VEDIO = PATH_BASE + CACHE_LOCAL_VEDIO + File.separator; //拍视频

    public static final String LOG_PATH = PATH_BASE + CACHE_LOG + File.separator; //日志系统保存
}
