package com.homelife.net.utils;


import com.homelife.net.config.Config;
import com.homelife.net.internal.HeadersInterceptor;
import com.homelife.net.internal.HttpHeaders;
import com.homelife.net.internal.Params;
import com.homelife.net.internal.ParamsInterceptor;
import com.homelife.util.ObjectUtil;
import com.homelife.util.StringUtil;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @Description 
 * @Author Xue
 * @CreateDate
 */
public final class OkHttpUtil {
    private static final String TAG = "OkHttpUtil";

    private OkHttpUtil() {
        throw new IllegalStateException("No instance!");
    }

    static OkHttpClient buildClient() {
        if (Config.isDebug()) {
            return buildDebugClient();
        } else {
            return buildReleaseClient();
        }
    }

    private static OkHttpClient buildDebugClient() {
        return buildReleaseClient().newBuilder()
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    private static OkHttpClient buildReleaseClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .retryOnConnectionFailure(Config.isRetryOnConnectionFailure())
                .connectTimeout(Config.getConnectTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(Config.getReadTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(Config.getWriteTimeout(), TimeUnit.MILLISECONDS);

        HttpHeaders httpHeaders = Config.getHttpHeaders();
        if (ObjectUtil.nonNull(httpHeaders)) {
            builder.addInterceptor(new HeadersInterceptor(httpHeaders));
        }

        Params params = Config.getParams();
        if (ObjectUtil.nonNull(params)) {
            builder.addInterceptor(new ParamsInterceptor(params));
        }

//        int[] certificates = Config.getCertificates();
//        String[] hosts = Config.getHosts();
//        if (ArrayUtil.notEmpty(certificates) && ArrayUtil.notEmpty(hosts)) {
//            Object[] sslSocketFactory = HttpsUtil.getSSLSocketFactory(Config.getContext(), certificates);
//            if (ArrayUtil.notEmpty(sslSocketFactory)) {
//                SSLSocketFactory factory = (SSLSocketFactory) sslSocketFactory[0];
//                X509TrustManager manager = (X509TrustManager) sslSocketFactory[1];
//                builder.sslSocketFactory(factory, manager);
//            }
//            HostnameVerifier hostnameVerifier = HttpsUtil.getHostnameVerifier(hosts);
//            if (ObjectUtil.nonNull(hostnameVerifier)) {
//                builder.hostnameVerifier(hostnameVerifier);
//            }
//        }

        try{
            //启用https模式
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[]{};
                }

                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }
            }};

            // creating an SSLSocketFactory that uses our TrustManager
            SSLContext sslContext = SSLContext.getInstance("SSL");//或者TLS
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            builder.sslSocketFactory(sslContext.getSocketFactory());
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

        }catch (Exception e){

        }


        List<Interceptor> interceptors = Config.getInterceptors();
        if (CollectionUtil.notEmpty(interceptors)) {
            for (Interceptor interceptor : interceptors) {
                if (ObjectUtil.isNull(interceptor)) {
                    continue;
                }
                builder.addInterceptor(interceptor);
            }
        }

        List<Interceptor> networkInterceptors = Config.getNetworkInterceptors();
        if (CollectionUtil.notEmpty(networkInterceptors)) {
            for (Interceptor networkInterceptor : networkInterceptors) {
                if (ObjectUtil.isNull(networkInterceptor)) {
                    continue;
                }
                builder.addNetworkInterceptor(networkInterceptor);
            }
        }

        return builder.build();
    }


    private static final RequestBody DEFAULT_REQUEST_BODY = new FormBody.Builder().build();

    public static RequestBody getDefaultRequestBody() {
        return DEFAULT_REQUEST_BODY;
    }

    /**
     * 根据Json 创建RequestBody
     *
     * @param json
     * @return
     */
    public synchronized static RequestBody createRequestBody(String json) {
        RequestBody requestBody = null;
        if (StringUtil.isBlank(json)) {
            requestBody = getDefaultRequestBody();
        } else if (StringUtil.isNotBlank(json)) {
            requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json);
        }
        return requestBody;
    }

    /**
     * 根据参数创建RequestBody
     *
     * @param params
     * @return
     */
    public synchronized static RequestBody createRequestBody(Map<String, String> params) {
        RequestBody requestBody = null;
        if (CollectionUtil.isEmpty(params)) {
            requestBody = getDefaultRequestBody();
        } else if (CollectionUtil.notEmpty(params)) {
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, String> me : params.entrySet()) {
                String key = me.getKey();
                String value = me.getValue();
                if (StringUtil.isBlank(key) || StringUtil.isBlank(value)) {
                    continue;
                }
                builder.add(key, value);
            }
            requestBody = builder.build();
        }
        return requestBody;
    }

    /**
     * 根据参数创建RequestBody
     *
     * @param json
     * @param params
     * @return
     */
    public synchronized static RequestBody createRequestBody(String json, Map<String, String> params) {
        RequestBody requestBody = null;
        if (StringUtil.isBlank(json) && CollectionUtil.isEmpty(params)) {
            requestBody = new FormBody.Builder().build();
        } else if (StringUtil.isBlank(json) && CollectionUtil.notEmpty(params)) {
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, String> me : params.entrySet()) {
                String key = me.getKey();
                String value = me.getValue();
                if (StringUtil.isBlank(key) || StringUtil.isBlank(value)) {
                    continue;
                }
                builder.add(key, value);
            }
            requestBody = builder.build();
        } else if (StringUtil.isNotBlank(json) && CollectionUtil.isEmpty(params)) {
            requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json);
        } else if (StringUtil.isNotBlank(json) && CollectionUtil.notEmpty(params)) {
            throw new IllegalArgumentException("can't post both json and bodyParams");
        } else {
            throw new IllegalArgumentException("bodyParams has key or value that is null");
        }
        return requestBody;
    }
}
