package com.homelife.util;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * @Description SDCard工具
 * @Author Xue
 * @CreateDate
 */
public final class SdCardUtil {
    private SdCardUtil() {
        throw new AssertionError();
    }

    /**
     * @Description 判断SDCard是否可用
     * @Author Xue
     * @CreateDate
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * @Description 获取SD卡路径
     * @Author Xue
     * @CreateDate
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;

    }

    /**
     * @Description 获取SD卡的剩余容量 单位byte
     * @Author Xue
     * @CreateDate
     */
    public static long getSDCardAllSize() {
        if (isSDCardEnable()) {
            StatFs stat = new StatFs(getSDCardPath());
            long availableBlocks = stat.getAvailableBlocksLong() - 4;// 获取空闲的数据块的数量
            long freeBlocks = stat.getAvailableBlocksLong();// 获取单个数据块的大小（byte）
            return freeBlocks * availableBlocks;
        }
        return 0;
    }

    /**
     * @return 容量字节 SDCard可用空间，内部存储可用空间
     * @Description 获取指定路径所在空间的剩余可用容量字节数，单位byte
     * @Author Xue
     * @CreateDate
     */
    public static long getFreeBytes(String filePath) {
        if (filePath.startsWith(getSDCardPath())) {// 如果是sd卡的下的路径，则获取sd卡可用容量
            filePath = getSDCardPath();
        } else {// 如果是内部存储的路径，则获取内存存储的可用容量
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = stat.getAvailableBlocksLong() - 4;
        return stat.getBlockSizeLong() * availableBlocks;
    }

    /**
     * @Description 获取系统存储路径
     * @Author Xue
     * @CreateDate
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }
}
