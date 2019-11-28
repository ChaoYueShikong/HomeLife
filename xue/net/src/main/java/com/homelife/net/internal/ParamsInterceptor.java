package com.homelife.net.internal;

import com.homelife.net.utils.CollectionUtil;
import com.homelife.util.ObjectUtil;
import com.homelife.util.StringUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @Description 请求时Params拦截器
 * @Author Xue
 * @CreateDate
 */
public class ParamsInterceptor extends BaseInterceptor<Params> {
    private static final String POST = "POST";
    //只能上传键值对,并且键值对都是间隔分开的,会将表单内的数据转换为键值对，比如,name=Java&age = 23
    private static final String X_WWW_FORM_URLENCODED = "x-www-form-urlencoded";

    public ParamsInterceptor(Params params) {
        super(params);
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestNewBuilder = request.newBuilder();
        Params params = getParams();
        injectQueryParams(request, requestNewBuilder, params);
        injectBodyParams(request, requestNewBuilder, params);
        Request newRequest = requestNewBuilder.build();
        return chain.proceed(newRequest);
    }

    /**
     * 注入QueryParams
     * whatever it's GET or POST
     *
     * @param request
     * @param builder
     * @param params
     */
    private void injectQueryParams(Request request, Request.Builder builder, Params params) {
        Map<String, String> queryParams = params.getQueryParams();
        if (CollectionUtil.notEmpty(queryParams)) {
            HttpUrl.Builder httpUrlBuilder = request.url().newBuilder();
            for (Map.Entry<String, String> me : queryParams.entrySet()) {
                String key = me.getKey();
                String value = me.getValue();
                if (StringUtil.isBlank(key) || StringUtil.isBlank(value)) {
                    continue;
                }
                httpUrlBuilder.addQueryParameter(key, value);
            }
            builder.url(httpUrlBuilder.build());
        }
    }

    /**
     * 注入BodyParams
     *
     * @param request
     * @param builder
     * @param params
     */
    private void injectBodyParams(Request request, Request.Builder builder, Params params) {
        Map<String, String> bodyParams = params.getBodyParams();
        if (CollectionUtil.isEmpty(bodyParams)) {
            return;
        }
        if (!canInjectBodyParams(request)) {
            return;
        }
        RequestBody requestBody = request.body();
        if (requestBody instanceof FormBody) {
            FormBody.Builder newFormBodyBuilder = new FormBody.Builder();
            for (Map.Entry<String, String> me : bodyParams.entrySet()) {//存入新值
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
            for (Map.Entry<String, String> me : bodyParams.entrySet()) {//存入新值
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
