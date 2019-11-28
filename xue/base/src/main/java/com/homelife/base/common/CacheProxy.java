package com.homelife.base.common;


import com.homelife.base.anno.Cache;
import com.homelife.base.anno.Form;
import com.homelife.base.anno.Url;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description 缓存代理的 (真实类与代理类的桥接器,调用器)
 * @Author Xue
 * @CreateDate
 */
public class CacheProxy implements InvocationHandler {

    public CacheProxy() { // 构造器
    }

    public CacheProxy(Object source) { // 构造器
        this.realObj = source;
    }

    /**
     * @return
     * @description 根据真实类，生产代理类
     * @author Xue
     * @paraminterClazz接口
     * @paramsource代理类
     */
    public static Object newProxyInstance(Class<?> interClazz, Object source) {
        if (!interClazz.isInterface()) { // 如不是接口，返回错误
            throw new IllegalArgumentException(interClazz.getName() + " is not an interface");
        }
        return Proxy.newProxyInstance(interClazz.getClassLoader(), new Class<?>[]{interClazz}, new CacheProxy(source));
    }

    private Object realObj; // 真实的类

    public Object getRealObj() {
        return realObj;
    }

    public void setRealObj(Object realObj) {
        this.realObj = realObj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable { // proxy 代理对象,真实的处理
        //Object res = method.invoke(realObj, args);
        Cache cache = method.getAnnotation(Cache.class);
        Url url = method.getAnnotation(Url.class);
        Form form = method.getAnnotation(Form.class);
        // Annotation[][] annz = method.getParameterAnnotations();
        // if(cache.value() == CacheType.GET){}
        switch (cache.value()) {
            case GET: // get类缓存

                break;
            case POST: // post类缓存

                break;
            case POST_SYNC: // 需要同步的缓存

                break;

            default:
                break;
        }

        return null;
    }

}
