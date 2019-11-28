package com.homelife.net.internal;

import android.support.annotation.NonNull;

import com.homelife.net.utils.CollectionUtil;
import com.homelife.util.StringUtil;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Description 请求时Header拦截器
 * @Author Xue
 * @CreateDate
 */
public class HeadersInterceptor extends BaseInterceptor<HttpHeaders> {

    public HeadersInterceptor(@NonNull HttpHeaders httpHeaders) {
        super(httpHeaders);
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder newBuilder = request.newBuilder();
        injectHeaders(newBuilder);
        injectAddHeaders(newBuilder);
        Request newRequest = newBuilder.build();
        return chain.proceed(newRequest);
    }

    /**
     * 注入唯一Headers
     *
     * @param builder
     */
    private void injectHeaders(Request.Builder builder) {
        Map<String, String> headers = getParams().getHeaders();
        if (CollectionUtil.notEmpty(headers)) {
            for (Map.Entry<String, String> me : headers.entrySet()) {
                String key = me.getKey();
                String value = me.getValue();
                if (StringUtil.isBlank(key) || StringUtil.isBlank(value)) {
                    continue;
                }
                builder.header(key, value);
            }
        }
    }

    /**
     * 注入非唯一Headers
     *
     * @param builder
     */
    private void injectAddHeaders(Request.Builder builder) {
        Map<String, String> addHeaders = getParams().getAddHeaders();
        if (CollectionUtil.notEmpty(addHeaders)) {
            for (Map.Entry<String, String> me : addHeaders.entrySet()) {
                String key = me.getKey();
                String value = me.getValue();
                if (StringUtil.isBlank(key) || StringUtil.isBlank(value)) {
                    continue;
                }
                builder.addHeader(key, value);
            }
        }
    }
}
