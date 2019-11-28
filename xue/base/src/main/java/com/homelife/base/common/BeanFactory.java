package com.homelife.base.common;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Description 工厂获取代理类
 * @Author Xue
 * @CreateDate
 */
public class BeanFactory {
    /**
     * @param clazz   真实类和代理类共同实现的接口
     * @param handler 真实类和代理类 个共同关联的调用器
     * @return 返回代理类实例
     * @description 生成动态代理类
     * @author Xue
     */
    @SuppressWarnings("unchecked")
    public static <T> T getInterfaceInstance(Class<T> clazz, InvocationHandler handler) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, handler);
    }
}
