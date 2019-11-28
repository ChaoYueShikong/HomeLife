package com.homelife.base.mvp;


import com.homelife.util.StringUtil;

/**
 * @Description 管理Model获取各种Model对象
 * @Author Xue
 * @CreateDate
 */
public final class ModelManager {
    private ModelManager() {
        throw new AssertionError();
    }

    /**
     * @Description 获取model.class的实例
     * @Author Xue
     * @CreateDate
     */
    public static <T> T getModelInstance(final Class<T> modelClass) {
        T modelInstance = null;
        try {
            modelInstance = modelClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return modelInstance;
    }

    /**
     * @Description 获取model.class的实例
     * @Author Xue
     * @CreateDate
     */
    public static <T> T getModelInstance(final String modelPkgName) {
        if (StringUtil.isBlank(modelPkgName)) {
            return null;
        }
        T modelInstance = null;
        try {
            modelInstance = (T) Class.forName(modelPkgName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return modelInstance;
    }

}
