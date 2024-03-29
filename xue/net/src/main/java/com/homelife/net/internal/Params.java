package com.homelife.net.internal;

import android.support.v4.util.ArrayMap;

import com.homelife.net.utils.CollectionUtil;
import com.homelife.util.StringUtil;
import java.util.Map;

/**
 * @Description
 * @Author Xue
 * @CreateDate
 */
public class Params {
    private Map<String, String> mQueryParams;
    private Map<String, String> mBodyParams;

    private Params(Builder builder) {
        this.mQueryParams = builder.mQueryParams;
        this.mBodyParams = builder.mBodyParams;
    }

    public static class Builder {
        private Map<String, String> mQueryParams;
        private Map<String, String> mBodyParams;

        public Builder() {
            mBodyParams = new ArrayMap<>();
            mQueryParams = new ArrayMap<>();
        }

        public Builder bodyParam(String key, String value) {
//            StringUtil.checkNotBlank(key, "key is blank");
//            StringUtil.checkNotBlank(value, "value is blank");
//            mBodyParams.put(key, value);
            return this;
        }

        public Builder bodyParams(Map<String, String> params) {
            CollectionUtil.checkNotEmpty(params, "params are empty");
            mBodyParams.putAll(params);
            return this;
        }

        public Builder queryParam(String key, String value) {
            StringUtil.checkNotBlank(key, "key is blank");
            StringUtil.checkNotBlank(value, "value is blank");
            mQueryParams.put(key, value);
            return this;
        }

        public Builder queryParams(Map<String, String> params) {
            CollectionUtil.checkNotEmpty(params, "params are empty");
            mQueryParams.putAll(params);
            return this;
        }

        public Params build() {
//            if (CollectionUtil.isEmpty(mQueryParams) && CollectionUtil.isEmpty(mBodyParams)) {
//                throw new IllegalArgumentException("no params");
//            }
            return new Params(this);
        }
    }

    public Map<String, String> getBodyParams() {
        return mBodyParams;
    }

    public Map<String, String> getQueryParams() {
        return mQueryParams;
    }

}
