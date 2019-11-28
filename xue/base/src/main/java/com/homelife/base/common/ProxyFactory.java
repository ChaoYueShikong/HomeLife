package com.homelife.base.common;

import java.lang.reflect.InvocationHandler;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author Xue
 * @CreateDate
 */
public class ProxyFactory {
    public static Map<String, Object> beanMap = new HashMap<String, Object>();

    /**
     * @return 返回代理类的 实例Bean
     * @description 动态获取代理类实例
     * @author Xue
     * @param:interClazz真实对象和代理对象都需要实现的 接口类
     * @param:handler真实对象和代理对象的 动态代理调用器
     */
    @SuppressWarnings("unchecked")
    public static <T> T getProxyBean(Class<T> interClazz, InvocationHandler handler) {
        String key = interClazz.getName();
        if (beanMap.containsKey(key)) {
            return (T) beanMap.get(key);
        }
        T bean = BeanFactory.getInterfaceInstance(interClazz, handler);
        beanMap.put(key, bean);
        return bean;
    }

    /**
     * @param interClazz
     * @param className
     * @return
     * @description 获取缓存的代理实例
     * @author Xue
     */
    @SuppressWarnings("unchecked")
    public static <T> T getCacheProxy(Class<T> interClazz, String className) {
        T bean = null;
        try {
            String key = interClazz.getName();
            if (beanMap.containsKey(key)) {
                return (T) beanMap.get(key);
            }
            bean = getProxyBean(interClazz, new CacheProxy(Class.forName(className).newInstance()));
            beanMap.put(key, bean);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bean;
    }
}
