package com.homelife.xhs.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.homelife.xhs.appconfig.Constant;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 说明: retrofit2.0 封装类
 * 作者: fangkaijin on 2017/5/17.17:29
 * 邮箱: fangkaijin@gmail.com
 */

public class RetrofitUtil {

    private static Retrofit retrofit = null;

    public static void init(Context context)
    {
        //初始化OkHttpClient
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(
                message -> {
                    if (!TextUtils.isEmpty(message))
                    {
                        Log.e("retrofit2.0--------", message);
                    }
                }
        );
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(false)
                .cache(new Cache(new File(context.getExternalCacheDir().getAbsolutePath()), 10*3600*24))
                .connectTimeout(30*1000, TimeUnit.MILLISECONDS)
                .readTimeout(30*1000, TimeUnit.MILLISECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                //设置OKHttpClient
                .client(client)
                //设置baseUrl,注意，baseUrl必须后缀"/"
                .baseUrl(Constant.BASE_URL)
                //添加Gson转换器
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    /**
     * youtu身份证识别专用
     * @return
     */
    public static Retrofit obtainRetrofitYouTu(Context context)
    {
        Retrofit retrofitYouTu = null;
        //初始化OkHttpClient
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(
                new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        if (!TextUtils.isEmpty(message))
                        {
                            Log.e("retrofit2.0--------", message);
                        }
                    }
                }
        );
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(false)
                .cache(new Cache(new File(context.getExternalCacheDir().getAbsolutePath()), 10*3600*24))
                .connectTimeout(30*1000, TimeUnit.MILLISECONDS)
                .readTimeout(30*1000, TimeUnit.MILLISECONDS)
                .build();
        retrofitYouTu = new Retrofit.Builder()
                //设置OKHttpClient
                .client(client)
                //设置baseUrl,注意，baseUrl必须后缀"/"
                .baseUrl(Constant.BASE_URL)
                //添加Gson转换器
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofitYouTu;
    }

    public static Retrofit obtainRetrofit()
    {
        if(null!=retrofit)
        {
            return retrofit;
        }
        else
        {
            return null;
        }
    }
}
