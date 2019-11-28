/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */
package com.homelife.util;

import android.annotation.SuppressLint;
import android.util.Base64;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;


/**
 * @author Xue
 * @version 1.0
 * @description DES简单对称加密（重要数据本地存储加密）
 */
public final class DesUtil {
    public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";

    /**
     * @return
     * @description
     * @author Xue
     */
    public static String encode(String data) {
//        try {
//            return encode(decryptOriginKey(BuildConfig.JAQ_DES_KEY_STRING), data.getBytes());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return "";
    }


    /**
     * @throws Exception
     * @description 字符串加密
     * @author Xue
     * @param:key加密私钥长度不能够小于8位
     * @param:data待加密字符串
     * @return:加密后的字节数组
     */
    public static String encode(String key, String data) throws Exception {
        return encode(key, data.getBytes());
    }

    /**
     * @throws Exception
     * @description 数据加密
     * @author Xue
     * @param:key加密私钥长度不能够小于8位
     * @param:data待加密字符串
     * @return加密后的字节数组一般结合Base64编码使用
     */
    @SuppressLint("TrulyRandom")
    public static String encode(String key, byte[] data) throws Exception {
        try {
            DESKeySpec dks = new DESKeySpec(key.getBytes()); // 创建一个DESKeySpec对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES"); //创建一个密匙工厂，然后用它把DESKeySpec转换成
            Key secretKey = keyFactory.generateSecret(dks); // key的长度不能够小于8位字节
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES); //Cipher对象实际完成加密操作
            IvParameterSpec iv = null;
//            IvParameterSpec iv = new IvParameterSpec(decryptOriginKey(BuildConfig.JAQ_DES_KEY_IVPARAMETER).getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec); //用密匙初始化Cipher对象

            byte[] bytes = cipher.doFinal(data);

            return new String(Base64.encode(bytes, Base64.DEFAULT));
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * @throws Exception
     * @description
     * @author Xue
     * @param:key解密私钥长度不能够小于8位
     * @param:data待解密字符串
     * @return:解密后的字节数组
     */
    public static byte[] decode(String key, byte[] data) throws Exception {
        try {
            //SecureRandom sr = new SecureRandom(); // DES算法要求有一个可信任的随机数源
            DESKeySpec dks = new DESKeySpec(key.getBytes()); // 创建一个DESKeySpec对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES"); // 创建一个密匙工厂
            Key secretKey = keyFactory.generateSecret(dks); // key的长度不能够小于8位字节
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            IvParameterSpec iv = null;
//            IvParameterSpec iv = new IvParameterSpec(decryptOriginKey(BuildConfig.JAQ_DES_KEY_IVPARAMETER).getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec); // 用密匙初始化Cipher对象
            return cipher.doFinal(data); // 真正开始解密操作
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * @return
     * @description 获取编码后的值
     * @author Xue
     * @param:key
     * @param:data
     */
    public static String decodeValue(String key, String data) {
        byte[] datas;
        String value = null;
        try {
            if (System.getProperty("os.name") != null
                    && (System.getProperty("os.name").equalsIgnoreCase("sunos") || System.getProperty("os.name").equalsIgnoreCase("linux"))) {
                datas = decode(key, Base64.decode(data, Base64.DEFAULT));
            } else {
                datas = decode(key, Base64.decode(data, Base64.DEFAULT));
            }
            value = new String(datas);
        } catch (Exception e) {
            value = "";
        }
        return value;
    }

    /**
     * @param data
     * @return
     * @description 获取编码后的值
     * @author Xue
     */
    public static String decode(String data) {
//        try {
//            return decodeValue(decryptOriginKey(BuildConfig.JAQ_DES_KEY_STRING), data);
//        } catch (JAQException e) {
//            e.printStackTrace();
//        }
        return "";
    }


    /**
     * @Description AES加密
     * @Author Xue
     * @CreateDate
     */
    public static String encrypt(String s) {
//        try {
//            return CipherUtil.encryptString(s, decryptOriginKey(BuildConfig.JAQ_AES_KEY_SERVER));
//        } catch (JAQException e) {
//            e.printStackTrace();
//        }
        return "";
    }

    /**
     * @Description AES解密
     * @Author Xue
     * @CreateDate
     */
    public static String decrypt(String s) {
//        try {
//            return CipherUtil.decryptString(s, decryptOriginKey(BuildConfig.JAQ_AES_KEY_SERVER));
//        } catch (JAQException e) {
//            e.printStackTrace();
//        }
        return "";
    }

    /**
     * 使用聚安全解密得到原始密钥
     *
     * @param encryptedKey 聚安全加密后的密钥
     * @return 解密后的密钥
     */
//    private static String decryptOriginKey(String encryptedKey) throws JAQException {
//        //安全加解密
//        SecurityCipher securityCipher = new SecurityCipher(Static.CONTEXT.getApplicationContext());
//        return securityCipher.decryptString(encryptedKey, BuildConfig.JAQ_SECURITY_KEY);
//    }
}
