package com.homelife.xhs.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.homelife.xhs.MainApplication;
import com.homelife.xhs.R;
import com.homelife.xhs.appconfig.Constant;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.NetworkInterface;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by
 */

public class CommonUtils {
    /**
     * 隐藏键盘
     */
    public static void hide_keyboard(View view) {
   /*     ((InputMethodManager) getSystemServce(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);*/
        InputMethodManager imm = (InputMethodManager) MainApplication.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
        Objects.requireNonNull(imm).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 显示键盘
     */
    public static void showKeyBoard() {
        ((InputMethodManager) Objects.requireNonNull(MainApplication.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE)))
                .toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 计算服务费  参数手机价格和费率
     *
     * @return 服务费
     */
    public static String getServiceFee(String rate, String price) {
        BigDecimal decimal = new BigDecimal(Double.parseDouble(rate));//费率
        BigDecimal phonePrice = new BigDecimal(Double.parseDouble(price));//手机价格
        return phonePrice.multiply(decimal).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static boolean isMobile(String mobile) {
        return Pattern.matches("^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$", mobile);
    }

    public static boolean isPassword(String password) {
        return Pattern.matches("^[a-zA-Z0-9]{6,16}$", password);
    }

    /**
     * 获取drawable文件
     *
     * @param context
     * @param id
     * @return 返回一个drawable对象
     */
    public static Drawable getDrawable(Context context, int id) {
        return context.getResources().getDrawable(id);
    }

    /**
     * 获取资源文件中的String字符串
     *
     * @param context
     * @param id
     * @return
     */
    public static String getString(Context context, int id) {
        return context.getResources().getString(id);
    }

    /**
     * 获取资源文件中的String[]
     *
     * @param context
     * @param id
     * @return
     */
    public static String[] getStringArray(Context context, int id) {
        return context.getResources().getStringArray(id);
    }

    /**
     * 获取资源文件中的颜色
     *
     * @param id
     * @return
     */
    public static int getColor(int id) {
        return MainApplication.getInstance().getResources().getColor(id);
    }

    /**
     * 得到json文件中的内容
     *
     * @param fileName
     * @return
     */
    public static String getJson(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = MainApplication.getInstance().getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }



    /**
     * 加载网络图片
     *
     * @param url
     * @param view
     */
    public static void loadImg(Object url, ImageView view) {
        if (url == null) return;

        if (url instanceof String) { //  不是url 直接加载
            if (!((String) url).startsWith("http")) { //不是http开头的url才加前缀
                url = getUrl((String) url);
            }
            Picasso.get().load((String) url).into(view);
        }
        if (url instanceof Integer) {
            Picasso.get().load((Integer) url).into(view);
        }

        if (url instanceof Uri) {
            Picasso.get().load((Uri) url).into(view);
        }

//        GlideApp.with(App.getInstance())
//                .load(url)
////                .placeholder(R.mipmap.ic_launcher)//占位图
////                .error(R.mipmap.ic_launcher)//错误图
////                .fallback(R.mipmap.ic_launcher)//url为空时
//                .dontAnimate()
//                .skipMemoryCache(false)
//                .priority(Priority.HIGH)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)//加载所有尺寸
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        e.printStackTrace();
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        return false;
//                    }
//                })
//                .into(view);

    }

    private static String getUrl(String url) {
        return Constant.IMG_PRE + url;
    }


    /**
     * 设置文字前后大小不一致
     * 1设置前面字大后面字小  倒数第一个字小
     * 其他设置前面字小后面字大
     */
    public static void setTextPreSize(TextView textView, String text, int i) {
        SpannableString spannableString = new SpannableString(text);
        RelativeSizeSpan sizeSpan01 = new RelativeSizeSpan(0.6f);
        if (i == 1) {
            spannableString.setSpan(sizeSpan01, text.length() - 1, text.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        } else {
            spannableString.setSpan(sizeSpan01, 0, 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        }
        textView.setText(spannableString);

    }

    /**
     * 设置中间文字颜色
     */
    public static SpannableString setForegroundSpannableString(String str) {
        SpannableString spannableString = new SpannableString(str);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getColor(R.color.color_fc3358));
        spannableString.setSpan(colorSpan, str.indexOf(":") + 1, str.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 设置控件隐藏
     *
     * @param views
     */
    public static void setViewGone(View... views) {
        for (View view : views) {
            view.setVisibility(View.GONE);
        }
    }

    /**
     * 设置控件显示
     *
     * @param views
     */
    public static void setViewVisible(View... views) {
        for (View view : views) {
            view.setVisibility(View.VISIBLE);
        }
    }

    /**
     * date2比date1多的天数
     *
     * @return
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取两个日期之间的间隔天数
     * 参数string类型的时间戳
     *
     * @return
     */
    public static String getGapCount(String startTime, String endTime) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        Date startDate = null;
        Date endDate = null;
        try {
            startDate = sdf.parse(sdf.format(new Date(Long.valueOf(startTime))));
            endDate = sdf.parse(sdf.format(new Date(Long.valueOf(endTime))));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return String.valueOf((int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24)));
    }

    /**
     * 判断字符串是否全是字母
     *
     * @param str
     * @return
     */
    public static boolean isName(String str) {
        Pattern pattern = Pattern.compile("^[0-9a-zA-Z\\u4e00-\\u9fa5]$");
        Matcher isLetter = pattern.matcher(str);
        return isLetter.matches();
    }

    /**
     * 过滤用户通讯录姓名
     *
     * @param name 通讯录姓名
     * @return filter_name 过滤后的姓名
     */
    public static String filterName(String name) {
        if (TextUtils.isEmpty(name)) {
            return "";
        } else {
            char[] chars = name.toCharArray();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < chars.length; i++) {
                if (isName(chars[i] + "")) {
                    sb.append(chars[i]);
                }
            }
            if (TextUtils.isEmpty(sb.toString())) {
                return "";
            } else {
                return sb.toString();
            }
        }
    }

    /**
     * 通讯录号码过滤
     *
     * @param phoneNum
     * @return
     */
    public static String filterPhoneNum(String phoneNum) {
        if (TextUtils.isEmpty(phoneNum)) {
            return "";
        } else {
            char[] chars = phoneNum.toCharArray();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < chars.length; i++) {
                if (isAllNum(chars[i] + "")) {
                    sb.append(chars[i]);
                }
            }
            if (TextUtils.isEmpty(sb.toString())) {
                return "";
            } else {
                return sb.toString();
            }
        }
    }

    /**
     * 判断字符串是否全是数字
     *
     * @param str
     * @return
     */
    public static boolean isAllNum(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(float dpValue) {
        final float scale = MainApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static String getDeviceInfo(Context context) {
        try {
            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String device_id = null;
            if (checkPermission(context, Manifest.permission.READ_PHONE_STATE)) {
                device_id = tm.getDeviceId();
            }
            String mac = getMac(context);

            json.put("mac", mac);
            if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }
            if (TextUtils.isEmpty(device_id)) {
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(),
                        android.provider.Settings.Secure.ANDROID_ID);
            }
            json.put("device_id", device_id);
            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMac(Context context) {
        String mac = "";
        if (context == null) {
            return mac;
        }
        if (Build.VERSION.SDK_INT < 23) {
            mac = getMacBySystemInterface(context);
        } else {
            mac = getMacByJavaAPI();
            if (TextUtils.isEmpty(mac)) {
                mac = getMacBySystemInterface(context);
            }
        }
        return mac;

    }

    @TargetApi(9)
    private static String getMacByJavaAPI() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface netInterface = interfaces.nextElement();
                if ("wlan0".equals(netInterface.getName()) || "eth0".equals(netInterface.getName())) {
                    byte[] addr = netInterface.getHardwareAddress();
                    if (addr == null || addr.length == 0) {
                        return null;
                    }
                    StringBuilder buf = new StringBuilder();
                    for (byte b : addr) {
                        buf.append(String.format("%02X:", b));
                    }
                    if (buf.length() > 0) {
                        buf.deleteCharAt(buf.length() - 1);
                    }
                    return buf.toString().toLowerCase(Locale.getDefault());
                }
            }
        } catch (Throwable e) {
        }
        return null;
    }

    private static String getMacBySystemInterface(Context context) {
        if (context == null) {
            return "";
        }
        try {
            WifiManager wifi = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (checkPermission(context, Manifest.permission.ACCESS_WIFI_STATE)) {
                WifiInfo info = wifi.getConnectionInfo();
                return info.getMacAddress();
            } else {
                return "";
            }
        } catch (Throwable e) {
            return "";
        }
    }

    public static boolean checkPermission(Context context, String permission) {
        boolean result = false;
        if (context == null) {
            return result;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                Class<?> clazz = Class.forName("android.content.Context");
                Method method = clazz.getMethod("checkSelfPermission", String.class);
                int rest = (Integer) method.invoke(context, permission);
                result = rest == PackageManager.PERMISSION_GRANTED;
            } catch (Throwable e) {
                result = false;
            }
        } else {
            PackageManager pm = context.getPackageManager();
            if (pm.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 校验姓名是否合格 2到14位汉字，可包含“ · ”
     *
     * @param name 姓名
     * @return
     */
    public static boolean isValidName(String name) {
        if (name.length() < 2 || name.length() > 14) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[\u4e00-\u9fa5]+(·[\u4e00-\u9fa5]+)*$");
        Matcher isName = pattern.matcher(name);
        if (!isName.matches()) {
            return false;
        }
        return true;
    }
    public static void skipBrowser(Activity activity, String linkUrl) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(linkUrl));
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
