package com.homelife.xhs.biz.common.net;


import com.homelife.net.utils.CollectionUtil;
import com.homelife.util.ObjectUtil;
import com.homelife.util.StringUtil;
import com.homelife.xhs.appconfig.Constant;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @Description
 * @Author Xue
 * @CreateDate
 */
public class SdInterceptor implements Interceptor {
    private static final String POST = "POST";
    private static final String X_WWW_FORM_URLENCODED = "x-www-form-urlencoded";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestNewBuilder = request.newBuilder();


//        Map<String, String> headers = new HashMap<>(1);
//        headers.put(Constant.Header.Key.ACCEPT, Constant.Header.Value.ACCEPT);
//        injectHeaders(requestNewBuilder, headers);

//        Map<String, String> params = new HashMap<>(2);
//        params.put(Constant.Param.Key.APP_VERSION, Constant.Param.Value.APP_VERSION);
//        params.put(Constant.Param.Key.TERMINAL, Constant.Param.Value.TERMINAL);
//        injectBodyParams(request, requestNewBuilder, params);

        Map<String, String> headers = new HashMap<>(1);
        headers.put("account-id", "");
        headers.put("auth-token", "");
        headers.put("ver", "1");
        injectHeaders(requestNewBuilder, headers);

        Request newRequest = requestNewBuilder.build();
        return chain.proceed(newRequest);
    }


    private Map<String, String> getOriginalRequestParams(Request request) {
        Map<String, String> originalRequestParams = new HashMap<>();
        RequestBody requestBody = request.body();
        if (requestBody instanceof FormBody) {
            FormBody formBody = (FormBody) requestBody;
            int size = formBody.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    String key = formBody.name(i);
                    String value = formBody.value(i);
                    originalRequestParams.put(key, value);
                }
            }
        }
        return originalRequestParams;
    }

    private void injectHeaders(Request.Builder requestNewBuilder, Map<String, String> headers) {
        for (Map.Entry<String, String> me : headers.entrySet()) {
            requestNewBuilder.addHeader(me.getKey(), me.getValue());
        }
    }



    private void injectBodyParams(Request request, Request.Builder builder, Map<String, String> params) {
        if (CollectionUtil.isEmpty(params)) {
            return;
        }
        if (!canInjectBodyParams(request)) {
            return;
        }
        RequestBody requestBody = request.body();
        if (requestBody instanceof FormBody) {
            FormBody.Builder newFormBodyBuilder = new FormBody.Builder();
            for (Map.Entry<String, String> me : params.entrySet()) {//存入新值
                String key = me.getKey();
                String value = me.getValue();
                if (StringUtil.isBlank(key) || StringUtil.isBlank(value)) {
                    continue;
                }
                newFormBodyBuilder.add(key, value);
            }
            FormBody oldFormBody = (FormBody) requestBody;
            int size = oldFormBody.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    String key = oldFormBody.name(i);
                    String value = oldFormBody.value(i);
                    newFormBodyBuilder.add(key, value);//取出旧的，存到新的
                }
            }
            builder.post(newFormBodyBuilder.build());
        } else if (requestBody instanceof MultipartBody) {
            MultipartBody.Builder newMultipartBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            for (Map.Entry<String, String> me : params.entrySet()) {//存入新值
                String key = me.getKey();
                String value = me.getValue();
                if (StringUtil.isBlank(key) || StringUtil.isBlank(value)) {
                    continue;
                }
                newMultipartBuilder.addFormDataPart(key, value);
            }
            MultipartBody oldMultipartBody = (MultipartBody) requestBody;
            List<MultipartBody.Part> parts = oldMultipartBody.parts();
            if (CollectionUtil.notEmpty(parts)) {
                for (MultipartBody.Part part : parts) {
                    newMultipartBuilder.addPart(part);//取出旧的，存到新的
                }
            }
            builder.post(newMultipartBuilder.build());
        }
    }

    /**
     * 判断是否可以写入BodyParams
     *
     * @param request
     * @return
     */
    private boolean canInjectBodyParams(Request request) {
        if (ObjectUtil.isNull(request)) {
            return false;
        }
        String method = request.method();
        if (!(POST.equals(method))) {
            return false;
        }
        RequestBody body = request.body();
        if (ObjectUtil.isNull(body)) {
            return false;
        }
        MediaType mediaType = body.contentType();
        if (ObjectUtil.isNull(mediaType)) {
            return false;
        }
        String subtype = mediaType.subtype();
        if (!(X_WWW_FORM_URLENCODED.equals(subtype))) {
            return false;
        }
        return true;
    }
}
