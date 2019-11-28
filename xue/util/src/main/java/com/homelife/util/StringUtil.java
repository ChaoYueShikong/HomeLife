package com.homelife.util;

import android.util.Log;
import com.homelife.util.signutil.Charsets;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description 字符串工具
 * @Author Xue
 * @CreateDate
 */
public final class StringUtil {
    public static final String TAG = "StringUtil";

    public static final int INDEX_NOT_FOUND = -1;

    private StringUtil() {
        throw new AssertionError();
    }

    /**
     * @param str
     * @return
     * @description 去除所有空白
     * @author Xue
     */
    public static String trimAllWhitespace(String str) {
        return str.replaceAll(" ", "").replaceAll("\n", "").replaceAll("\t", "").toString();
    }

    /**
     * @param str
     * @param stripChars
     * @return
     * @description 截取到指定的字符
     * @author Xue
     */
    public static String stripEnd(String str, String stripChars) {
        int end;
        if (str == null || (end = str.length()) == 0) {
            return str;
        }
        if (stripChars == null) {
            while ((end != 0) && Character.isWhitespace(str.charAt(end - 1))) {
                end--;
            }
        } else if (stripChars.length() == 0) {
            return str;
        } else {
            while ((end != 0) && (stripChars.indexOf(str.charAt(end - 1)) != INDEX_NOT_FOUND)) {
                end--;
            }
        }
        return str.substring(0, end);
    }

    /**
     * @param str
     * @return
     * @description null转换为"0"
     * @author Xue
     */
    public static String null2Zero(String str) {
        if (str == null || str.equals("")) {
            return "0";
        } else {
            return str;
        }

    }

    /**
     * @param str
     * @return true全是数字 fasle含有字符
     * @description 判断字符串是否全是数字
     * @author Xue
     */
    public static boolean isNumeric(String str) {
        if (isBlank(str)) {
            return false;
        }
        //Pattern pattern = Pattern.compile("[0-9]*");//0-9的数字
        Pattern pattern = Pattern.compile("^[0-9]+\\.?[0-9]*$");//0-9的数字和一个小数点
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * @param str
     * @return 9
     * @description 剔除包含数字的字符串中的字符
     * @author Xue
     */
    public static String rmStringInNum(String str) {
        String str1 = "";
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                str1 += str.charAt(i);
            }
        }
        return str1;
    }

    /**
     * @param mobiles
     * @return
     * @description 电话号码正则式校验
     * @author Xue
     */
    public static boolean isMobileNo(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * @param str
     * @param code 默认编码GBK(中文2字节，英文1字节)UTF-8(中文3字节，英文1字节)ISO8859-1(中文1字节，英文1字节)
     * @return 混合字符长度
     * @description 获取各种编码的字符串长度
     * @author Xue
     */
    public static int getStringLength(String str, String code) {
        int length = 0;
        try {
            byte[] bt = str.getBytes(code);
            length = bt.length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return length;
    }

    /**
     * @param str
     * @return 混合字符长度
     * @description 默认GBK编码下的字符串长度
     * @author Xue
     */
    public static int getStringLength(String str) {
        return getStringLength(str, "GBK");
    }

    /**
     * @description String转int
     * @author Xue
     * @version 1.0
     */
    public static int string2Int(String str) {
        int a = 0;
        if (isNumeric(str)) {
            a = Integer.valueOf(str);
        }
        return a;
    }

    /**
     * @description String转int
     * @author Xue
     * @version 1.0
     */
    public static long string2Long(String str) {
        long a = 0l;
        if (isNumeric(str)) {
            a = Long.valueOf(str);
        }
        return a;
    }

    /**
     * @description String转Float
     * @author Xue
     * @version 1.0
     */
    public static float string2Float(String str) {
        float a = 0.0f;
        if (isNumeric(str)) {
            a = Float.valueOf(str);
        }
        return a;
    }

    /**
     * @description String转Double
     * @author Xue
     * @version 1.0
     */
    public static double string2Double(String str) {
        double a = 0.0f;
        if (isNumeric(str)) {
            a = Double.valueOf(str);
        }
        return a;
    }

    /**
     * @description String 转 BigDecimal
     * @author Xue
     * @version 1.0
     */
    public static BigDecimal string2BigDecimal(String str) {
        BigDecimal big = new BigDecimal(0);
        if (isNumeric(str)) {
            big = new BigDecimal(str);
        }
        return big;
    }

    /**
     * @description 判断对象是否为空
     * @author Xue
     * @version 1.0
     */
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }

    /**
     * @param str
     * @return
     * @description String不为空
     * @author Xue
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * <p>Checks if a String is whitespace, empty ("") or null.</p>
     * <p>
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is null, empty or whitespace
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        if ("null".equalsIgnoreCase(str)) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if a String is not whitespace, empty ("") or null.
     *
     * @param str the String to check, may be null
     * @return the input <code>str<code/> if it's not blank, otherwise
     * throw {@link IllegalArgumentException}.
     * @throws IllegalArgumentException if the input <code>str<code/> is blank.
     */
    public static String requireNotBlank(String str) {
        if (isBlank(str)) {
            throw new IllegalArgumentException("Blank string");
        } else {
            return str;
        }
    }

    /**
     * Check if a String is not whitespace, empty ("") or null.
     *
     * @param str the String to check, may be null
     * @return the input <code>str<code/> if it's not blank, otherwise
     * throw {@link IllegalArgumentException}.
     * @throws IllegalArgumentException if the input <code>str<code/> is blank.
     */
    public static String requireNotBlank(String str, String msg) {
        if (isBlank(str)) {
            throw new IllegalArgumentException(msg);
        } else {
            return str;
        }
    }

    public static void checkNotBlank(String str) {
        if (isBlank(str)) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkNotBlank(String str, String msg) {
        if (isBlank(str)) {
            throw new IllegalArgumentException(msg);
        }
    }

    /**
     * Encodes the given string into a sequence of bytes using the UTF-8 charset, storing the result into a new byte
     * array.
     *
     * @param string
     *            the String to encode, may be {@code null}
     * @return encoded bytes, or {@code null} if the input string was {@code null}
     * @throws NullPointerException
     *             Thrown if {@link Charsets#UTF_8} is not initialized, which should never happen since it is
     *             required by the Java platform specification.
     * @since As of 1.7, throws {@link NullPointerException} instead of UnsupportedEncodingException
     * @see <a href="http://download.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html">Standard charsets</a>
     * @see #//getBytesUnchecked(String, String)
     */
    public static byte[] getBytesUtf8(final String string) {
        return getBytes(string, Charsets.UTF_8);
    }

    /**
     * Calls {@link String#getBytes(Charset)}
     *
     * @param string
     *            The string to encode (if null, return null).
     * @param charset
     *            The {@link Charset} to encode the {@code String}
     * @return the encoded bytes
     */
    private static byte[] getBytes(final String string, final Charset charset) {
        if (string == null) {
            return null;
        }
        return string.getBytes(charset);
    }

    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (isBlank(hexString)) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * @description 测试
     * @author Xue
     * @createDate
     * @version 1.0
     */
    public static void main(String[] args) {
        Log.d(TAG,"==fhb===" + isNumeric("5"));
        Log.d(TAG,"==fhb2===" + stripEnd("wqfgdhgkj", "h"));
        Log.d(TAG,"==fhb3===" + string2Int("null"));
        Log.d(TAG,"==fhb4===" + string2Int(""));
        Log.d(TAG,"==fhb5===" + string2Float(""));
        Log.d(TAG,"==fhb6===" + string2Long(""));
        //Log.d(TAG,"==fhb6==="+Integer.valueOf("null"));
        Log.d(TAG,"==fhb7===" + Integer.parseInt(""));
        Log.d(TAG,"==fhb6===" + string2Long(""));
        Log.d(TAG,"==isNumeric===" + isNumeric("1231434.") + "");

    }
}
