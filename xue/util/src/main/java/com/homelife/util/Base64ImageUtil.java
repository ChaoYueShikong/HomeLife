package com.homelife.util;


import com.homelife.util.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Description Base64解密验证码
 * @Author Xue
 * @CreateDate 2017/9/22
 */
public final class Base64ImageUtil {
    private Base64ImageUtil() {
    }

    /**
     * @Description
     * @Author Xue
     * @CreateDate
     */
    public static String image2Str(File file) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream inputStream = new FileInputStream(file);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
            // 对字节数组Base64编码
            Base64 encoder = new Base64();
            return encoder.encodeAsString(data);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    /**
     * @Description
     * @Author Xue
     * @CreateDate
     */
    public static boolean str2Image(String str, String imgFile) {
        if (StringUtil.isBlank(str) || StringUtil.isBlank(imgFile)) {
            return false;
        }
        try {
            Base64 decoder = new Base64();
            byte[] bytes = decoder.decode(str); // Base64解码
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {
                    bytes[i] += 256;
                }
            }
            // 生成图片
            OutputStream out = new FileOutputStream(imgFile);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return false;
    }

    /**
     * @Description base64解密图片流
     * @Author Xue
     * @CreateDate
     */
    public static byte[] base64Str2Byte(String str) {
        Base64 decoder = new Base64();
        byte[] bytes = decoder.decode(str); // Base64解码
        return bytes;
    }

}
