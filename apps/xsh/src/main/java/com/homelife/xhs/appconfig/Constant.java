package com.homelife.xhs.appconfig;

import com.homelife.xhs.BuildConfig;

/**
 * @Description 配置
 * @Author Xue
 * @CreateDate
 */
public final class Constant {
    private Constant() {
        throw new IllegalStateException("No instance!");
    }

    public static final class Header {
        public static final class Key {
            public static final String ACCEPT = "Accept";
        }
        public static final class Value {
            public static final String ACCEPT = "application/json";

        }
    }

    public static final class Param {
        public static final class Key {
            public static final String APP_NAME = "app_name";
            public static final String APP_VERSION = "version";
            public static final String TERMINAL = "Terminal";
        }

        public static final class Value {
            public static final String APP_NAME = "心生活";
            public static final String APP_VERSION = BuildConfig.VERSION_NAME;
            public static final String TERMINAL = "android";

        }
    }

    public static final class H5 {
        public static final class Handler {
            public static final String DEMO_URL = "demo_url";
        }
    }

    public static final String BASE_URL = "http://122.112.196.223/app-api/";
    public static final String IMG_PRE = "http://jngjsc.51aoshuo.com/taurus";         //图片前缀
}
