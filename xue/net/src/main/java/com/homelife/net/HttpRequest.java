package com.homelife.net;

import com.homelife.net.internal.RealHttpRequest;
import com.homelife.util.ObjectUtil;
import io.reactivex.disposables.Disposable;

/**
 * @Description 
 * @Author Xue
 * @CreateDate
 */
public final class HttpRequest {
    private static RealHttpRequest sDelegate;

    private HttpRequest() {
        throw new IllegalStateException("No Instance!");
    }


    public static void init(Options options) {
        sDelegate = new RealHttpRequest().init(options);
    }

    private static RealHttpRequest checkAndGet() {
        return ObjectUtil.requireNonNull(sDelegate, "init() should be called first");
    }

    public static <T> T create(Class<T> service) {
        return checkAndGet().create(service);
    }

    public static void addCall(Disposable call) {
        checkAndGet().addCall(call);
    }

    public static void cancelCall(Disposable call) {
        checkAndGet().cancelCall(call);
    }

    public static void cancelAll() {
        checkAndGet().cancelAll();
    }
}
