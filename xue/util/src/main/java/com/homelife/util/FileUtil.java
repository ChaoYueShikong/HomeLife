/*
 * Copyright (c) 2012, Xue Corporation, All Rights Reserved
 */
package com.homelife.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import java.io.StringReader;
import java.io.StringWriter;
import javax.crypto.IllegalBlockSizeException;

/**
 * @author Xue
 * @Description 处理文件的公用类
 * @createDate
 */
public final class FileUtil {

    public static final String TAG = "FileUtil";
    public static final String PATH = "/hou";
    public static final String PATH_FACE = "/hou/face";
    public static long sFreeSpaceThreshold = 300 * 1024 * 1024;//单位Byte 300M
    public static long sFileSizeLimit = 8 * 1024 * 1024;//单位Byte 8M
    /**
     * @param filepath =/finshine/files/
     * @return /storage/sdcard0/finshine/files/ 或者 /mnt/sdcard/finshine/files 或者/data/data/<package name>/cache
     * @Description 文件要保存的路径
     * @author Xue
     * @createDate
     */
    public static File getCacheFileDir(String filepath) {
        File cacheDir;// 图片文件缓存目录
        // Find the dir to save cached images
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {// SD卡正常挂载
            cacheDir = new File(Environment.getExternalStorageDirectory(), filepath);// storage/sdcard0/nailao/files/或者/mnt/sdcard/nailao/files
        } else {
            cacheDir = Static.CONTEXT.getCacheDir();// 缓存目录 /data/data/<package name>/cache
        }
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        return cacheDir;
    }


    public static long getFolderSize(File file) {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            if (fileList != null && fileList.length > 0) {
                for (int i = 0; i < fileList.length; i++) {
                    if (fileList[i].isDirectory()) {
                        size = size + getFolderSize(fileList[i]);

                    } else {
                        size = size + fileList[i].length();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    public static void clearCache(File file) {
        try {
            File[] fileList = file.listFiles();
            if (fileList != null && fileList.length > 0) {
                for (int i = 0; i < fileList.length; i++) {
                    if (!fileList[i].isDirectory()) {
                        if (fileList[i].exists()) {
                            fileList[i].delete();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description
     * @author Xue
     * @createDate
     */
    public static boolean readFileAssets(Context context, String fileName, String descPath) {
        try {
            AssetManager am = context.getAssets();
            FileOutputStream fos = new FileOutputStream(FileUtil.getFile(descPath));
            BufferedInputStream bis = new BufferedInputStream(am.open(fileName));
            byte[] buf = new byte[8096];
            int readLength = 0;
            while ((readLength = bis.read(buf)) != -1) {
                fos.write(buf, 0, readLength);
            }
            fos.close();
            bis.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @Description 读取Asset文件
     * @author Xue
     * @createDate
     */
    public static String readFileAssets2String(Context context, String fileName) {
        try {
            AssetManager am = context.getAssets();
            return file2String(am.open(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description
     * @author Xue
     * @createDate
     */
    public static String file2String(InputStream inputStream) {
        InputStreamReader reader = null;
        StringWriter writer = new StringWriter();
        try {
            reader = new InputStreamReader(inputStream);
            // 将输入流写入输出流
            char[] buffer = new char[1024];
            int n = 0;
            while (-1 != (n = reader.read(buffer))) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return writer.toString();
    }

    /**
     * @Description
     * @author Xue
     * @createDate
     */
    public static boolean renameTo(String fileNamePath, String newFileNamePath) {
        File f = new File(fileNamePath);
        return f.renameTo(new File(newFileNamePath));
    }

    /**
     * @Description
     * @Author Xue
     * @CreateDate
     */
    public static String file2String(File file) {
        try {
            return file2String(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description
     * @author Xue
     * @createDate
     */
    public static boolean string2File(String res, String filePath) {
        boolean flag = true;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            File distFile = new File(filePath);
            if (distFile != null && distFile.getParentFile() != null) {
                if (!distFile.getParentFile().exists())
                    distFile.getParentFile().mkdirs();
                bufferedReader = new BufferedReader(new StringReader(res));
                bufferedWriter = new BufferedWriter(new FileWriter(distFile));
                char buf[] = new char[1024]; // 字符缓冲区
                int len;
                while ((len = bufferedReader.read(buf)) != -1) {
                    bufferedWriter.write(buf, 0, len);
                }
                bufferedWriter.flush();
                bufferedReader.close();
                bufferedWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
            return flag;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * @Description 根据路径和文件名获取文件
     * @author Xue
     * @createDate
     */
    @SuppressWarnings("resource")
    public static File getFile(String path, String fileName) {
        File file = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File(path, fileName);
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (Exception e) {
            }
            if (fileName.contains("txt")) {
                try {
                    FileOutputStream strem = new FileOutputStream(file);
                    byte[] bytes = new byte[10];
                    for (int i = 0; i < bytes.length; i++) {
                        bytes[i] = 's';
                    }
                    try {
                        strem.write(bytes);
                        strem.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
        // Log.i("file:", file.getAbsolutePath() + file.getName());
        return file;
    }

    /**
     * @Description 根据路径得到文件
     * @author Xue
     * @createDate
     */
    public static File getFile(String path) {
        try {
            File file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description 根据路径判断文件是否存在
     * @author Xue
     * @createDate
     */
    public static boolean exists(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     * @Description 根据路径删除文件
     * @author Xue
     * @createDate
     */
    public static boolean delete(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        return file.delete();
    }

    /**
     * @Description 根据路径创建文件
     * @author Xue
     * @createDate
     */
    public static File creatSDFile(String filepath, String fileNmae) {
        File file = new File(getCacheFileDir(filepath), fileNmae);// SDPATH + fileNmae
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * @Description 将数据流保存到SD卡当中
     * @author Xue
     * @createDate
     */
    public static void write2SDFromInput(String path, String fileName, InputStream inputStream) {
        Log.d(TAG,"path=" + path + ";fileName=" + fileName + ";");
        File file = null;
        // File folder = null;
        OutputStream output = null;
        try {
            // folder = creatSDDir(path);
            // Log.d(TAG,"folder=" + folder);
            // file = CreatSDFile(path + fileName);
            file = creatSDFile(path, fileName);
            Log.d(TAG,"andy_file=" + file);
            output = new FileOutputStream(file);
            byte buffer[] = new byte[4 * 1024];
            while ((inputStream.read()) != -1) {
                output.write(buffer);
            }
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // return file;
    }


    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }


    //删除文件夹
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description 图片存储地址
     * @Author Xue
     * @CreateDate
     */
    public static String getImgPath(){
        String path = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/hou/";//获取根目录
        }
        return path;
    }

    /**
     * 获取SD 卡内存
     * @return
     */
    public static long getAvailableSD() {
        if (!hasSdcard())
            return 0;
        String storageDirectory = Environment.getExternalStorageDirectory().toString();
        try {
            StatFs stat = new StatFs(storageDirectory);
            long avaliableSize = ((long) stat.getAvailableBlocks() * (long) stat
                    .getBlockSize());
            return avaliableSize;
        } catch (RuntimeException ex) {
            return 0;
        }
    }

    /**
     * 判断是否存在sd卡
     * @return
     */
    public static boolean hasSdcard() {
        String status = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(status);
    }

    /**
     * check sd card remount result and return the root directory
     *
     * @return
     */
    public static File getExternalStorageRootDirectory() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {// SD卡正常挂载
            return Environment.getExternalStorageDirectory();
        }
        return null;
    }

    /**
     * @Description 获取文件夹大小
     * @Author Xue
     * @CreateDate
     */
    public static long getFileSize(String pathname) {
        long size = -1;
        if (StringUtil.isBlank(pathname)) {
            return -1;
        }
        File file = new File(pathname);
        if (file == null) return size;
        try {
            if (file.isDirectory()) {
                File[] fileList = file.listFiles();
                if (fileList != null && fileList.length > 0) {
                    for (int i = 0; i < fileList.length; i++) {
                        if (fileList[i].isDirectory()) {

                            if (getFileSize(fileList[i].getPath()) == -1) {
                                size = size + 0;
                            } else {
                                size = size + getFileSize(fileList[i].getPath());
                            }
                        } else {
                            size = size + fileList[i].length();
                        }
                    }
                }
            } else {
                size = file.length();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * @Description
     * @author Xue
     * @createDate
     */
    public static String stream2String(InputStream inputStream) {
        InputStreamReader reader = null;
        StringWriter writer = new StringWriter();
        try {
            reader = new InputStreamReader(inputStream);
            // 将输入流写入输出流
            char[] buffer = new char[1024];
            int n = 0;
            while (-1 != (n = reader.read(buffer))) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return writer.toString();
    }

    /**
     * @Description
     * @author Xue
     * @createDate
     */
    public static boolean rename(String oldpathname, String newpathname) {

        if (StringUtil.isBlank(oldpathname) || StringUtil.isBlank(newpathname)) {
            return false;
        }
        File f = new File(oldpathname);
        return f.renameTo(new File(newpathname));
    }

    /**
     * @Description
     * @Author Xue
     * @CreateDate
     */
    public static String readString(String pathname) throws IllegalBlockSizeException {
        StringUtil.checkNotBlank(pathname, "Path name is blank!");
        File file = new File(pathname);
        if (file.length() > sFileSizeLimit) {
            throw new IllegalBlockSizeException(pathname + " size " + file.length() + " larger than " + sFileSizeLimit);
        }
        try {
            return stream2String(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 写字符串
     *
     * @param pathname full path + file name
     * @param content  String source
     * @return
     */
    public static boolean writeString(String pathname, String content) {
        boolean flag = true;
        if (StringUtil.isBlank(pathname)) {
            return false;
        }
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            File distFile = createIfNotExist(pathname);
            if (distFile != null) {
                bufferedReader = new BufferedReader(new StringReader(content));
                bufferedWriter = new BufferedWriter(new FileWriter(distFile));
                char buf[] = new char[1024]; // 字符缓冲区
                int len;
                while ((len = bufferedReader.read(buf)) != -1) {
                    bufferedWriter.write(buf, 0, len);
                }
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
            return flag;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * Creates a new <code>File</code> instance by converting the given
     * pathname string into an abstract pathname.  If the given string is
     * the empty string, then the result is the empty abstract pathname.
     *
     * @param filepath full path + file name
     * @return
     */
    private static File createFile(String filepath) {
        if (StringUtil.isBlank(filepath)) {
            return null;
        }
        if (!isAllowToWrite(filepath)) {
            return null;
        }
        try {
            File file = new File(filepath);
            if (!file.getParentFile().exists()) {
                createFolder(file.getParentFile().getPath());
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates the directory named by this abstract pathname.
     *
     * @return <code>true</code> if and only if the directory was
     * created; <code>false</code> otherwise
     */
    public static File createFolder(String filepath) {
        if (StringUtil.isBlank(filepath)) {
            return null;
        }
        if (!isAllowToWrite(filepath)) {
            return null;
        }
        try {
            File file = new File(filepath);
            if (!file.getParentFile().exists()) {
                createFolder(file.getParentFile().getPath());
            }
            if (!file.exists()) {
                file.mkdir();
            }
            return file;
        } catch (Exception e) {
            Log.w("UtilTest", "creatFolder " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates the directory or file named by this abstract pathname.
     *
     * @return <code>true</code> if and only if the directory was
     * created; <code>false</code> otherwise
     */
    public static File createIfNotExist(String pathname) {
        if (StringUtil.isBlank(pathname)) {
            return null;
        }
        File file = new File(pathname);// PATH + pathname
        if (!file.exists()) {
            createFile(pathname);
        }
        return file;
    }

    /**
     * @Description 将数据流保存到文件当中
     * @author Xue
     * @createDate
     */
    public static boolean writeStream(String pathname, BufferedInputStream inputStream) {
        boolean result = true;
        if (StringUtil.isBlank(pathname)) {
            return false;
        }
        if (!isAllowToWrite(pathname)) {
            return false;
        }
        File file = null;
        BufferedOutputStream output = null;
        try {
            file = createIfNotExist(pathname);
            if (file == null) return false;
            // 指定要写入文件的缓冲输出字节流
            output = new BufferedOutputStream(new FileOutputStream(file));
            byte[] bb = new byte[1024];// 用来存储每次读取到的字节数组
            int n;// 每次读取到的字节数组的长度
            while ((n = inputStream.read(bb)) != -1) {
                output.write(bb, 0, n);// 写入到输出流
            }
            output.flush();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        } finally {
            try {
                inputStream.close();// 关闭流
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 将对象保存到指定路径
     *
     * @param pathname 文件路径+文件名
     * @param content  对象
     * @return true 保存成功
     */
    public static boolean writeObject(String pathname, Object content) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        if (StringUtil.isBlank(pathname)) {
            return false;
        }
        if (!isAllowToWrite(pathname)) {
            return false;
        }
        File file = createIfNotExist(pathname);
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(content);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取对象
     *
     * @param pathname 文件路径+件名
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T readObject(String pathname, Class<T> clazz) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        if (StringUtil.isBlank(pathname)) {
            return null;
        }
        File file = new File(pathname);
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            T o = clazz.cast(ois.readObject());
            return o;
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
            return null;
        } catch (OptionalDataException e) {
            e.printStackTrace();
            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * check the system  internal memory or sd card free space,
     * all the write action need to call this api before write.
     *
     * @param pathname
     * @return
     */
    private static boolean isAllowToWrite(String pathname) {
        if (StringUtil.isBlank(pathname)) {
            return false;
        }
        if (pathname.contains(Environment.getExternalStorageDirectory().toString())) {
            //获取SD卡可用剩余空间
            long SDFreeSpace = Environment.getExternalStorageDirectory().getFreeSpace();
            //allow to write when space memory > 500M
            if (SDFreeSpace > sFreeSpaceThreshold) {
                return true;
            }
        } else if (pathname.contains(Environment.getDataDirectory().toString())) {
            //获取内部存储可用剩余空间
            long romFreeSpace = Environment.getDataDirectory().getFreeSpace();
            if (romFreeSpace > sFreeSpaceThreshold) {
                return true;
            }
        }
        return false;
    }

    /**
     * 内部存储或者SD存储剩余空间小于threshold值时，不能再写入
     *
     * @param threshold
     */
    public static void setFreespaceThreshold(long threshold) {
        sFreeSpaceThreshold = threshold;
    }

    /**
     * 向文件中以字节的形式写入数据
     *
     * @param pathname 目标文件全路径
     * @param data     要写入的数据
     * @return true表示写入成功  false表示写入失败
     */
    public static boolean writeBytes(String pathname, byte[] data) {
        FileOutputStream fos = null;
        if (StringUtil.isBlank(pathname)) {
            return false;
        }
        if (!isAllowToWrite(pathname)) {
            return false;
        }
        try {
            fos = new FileOutputStream(pathname);
            fos.write(data);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 从文件中以字节的形式读取数据
     *
     * @param pathname
     * @return
     */
    public static byte[] readBytes(String pathname) {
        FileInputStream fis = null;
        if (StringUtil.isBlank(pathname)) {
            return null;
        }
        try {
            fis = new FileInputStream(pathname);
            int len = fis.available();
            byte[] buffer = new byte[len];
            fis.read(buffer);
            return buffer;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * @Description 获取文件夹中指定后缀文件的数量
     * @Author Xue
     * @CreateDate 2019/1/7 上午2:39
     */
    public static int getFileSize(String path,String suffix){
        if(StringUtil.isBlank(path)){
            path = FileUtil.getCacheFileDir(FileUtil.PATH).getAbsolutePath() + "/";
        }
        int size = 0;

        File file = new File(path);
        if (!file.exists()) {
            return size;
        }
        if (!file.isDirectory()) {
            return size;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(suffix)) {
                size ++;
            }
        }
        return size;
    }

    /**
     * @Description 是否是空文件
     * @Author Xue
     * @CreateDate 2019/1/8 上午2:26
     */
    public static boolean isNullFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (file.exists()) {//文件存在
            if (file.length() <= 15000) {//文件小于15k
                flag = true;
            } else {
                flag = false;
            }
        } else {//文件不存在
            flag = true;
        }
        return flag;
    }

    /**
     * @Description 将文件转成base64 字符串
     * @Author Xue
     * @CreateDate 2019-04-26 02:06
     */
//    public static String encodeBase64File(String path) throws Exception {
//        File file = new File(path);
//        FileInputStream inputFile = new FileInputStream(file);
//        byte[] buffer = new byte[(int)file.length()];
//        inputFile.read(buffer);
//        inputFile.close();
//        return new BASE64Encoder().encode(buffer);
//    }

    /**
     * @Description 将base64字符解码保存文件
     * @Author Xue
     * @CreateDate 2019-04-26 02:07
     */
//    public static void decoderBase64File(String base64Code,String targetPath) throws Exception {
//        byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
//        FileOutputStream out = new FileOutputStream(targetPath);
//        out.write(buffer);
//        out.close();
//    }

    /**
     * @Description 将base64字符保存文本文件
     * @Author Xue
     * @CreateDate 2019-04-26 02:08
     */
    public static void toFile(String base64Code,String targetPath) throws Exception {
        byte[] buffer = base64Code.getBytes();
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }

}
