package com.homelife.net.utils;

import com.homelife.net.config.Config;
import com.homelife.util.ObjectUtil;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @Description 
 * @Author Xue
 * @CreateDate
 */
public final class RetrofitUtil {
    private RetrofitUtil() {
        throw new IllegalStateException("No instance!");
    }

    private static CompositeDisposable sCompositeDisposable;

    public static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpUtil.buildClient())
                .baseUrl(Config.getBaseUrl())
                .build();
    }

    public static void addCall(Disposable call) {
        if (ObjectUtil.isNull(call) || call.isDisposed()) {
            return;
        }
        synchronized (RetrofitUtil.class) {
            sCompositeDisposable = RxJavaUtil.getCompositeDisposable(sCompositeDisposable);
            if (sCompositeDisposable.isDisposed()) {
                return;
            }
            sCompositeDisposable.add(call);
        }
    }

    public static void cancelCall(Disposable call) {
        if (ObjectUtil.isNull(call) || call.isDisposed()) {
            return;
        }
        synchronized (RetrofitUtil.class) {
            if (sCompositeDisposable == null || sCompositeDisposable.isDisposed()) {
                return;
            }
            sCompositeDisposable.remove(call);
        }
    }

    public static void cancelAll() {
        synchronized (RetrofitUtil.class) {
            if (sCompositeDisposable == null) {
                return;
            }
            if (sCompositeDisposable.isDisposed()) {
                sCompositeDisposable = null;
                return;
            }
            sCompositeDisposable.clear();
            sCompositeDisposable = null;
        }
    }
}
