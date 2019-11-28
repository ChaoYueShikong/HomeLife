/*
 * Copyright (c) 2016 Corporation. All rights reserved.
 *
 */
package com.homelife.base.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

import com.homelife.base.anno.AfterViews;
import com.homelife.base.anno.BeforeViews;
import com.homelife.base.anno.Click;
import com.homelife.base.anno.Inflater;
import com.homelife.base.anno.RootLayout;
import com.homelife.base.anno.ViewById;
import com.homelife.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xue
 * @version 1.0
 * @description 控件注解解析
 */
public abstract class Annotation4View {

    private Object mDelegate;

    protected LayoutInflater mLayoutInflater;

    private View mRootView;

    public Annotation4View(Object mDelegate, LayoutInflater mLayoutInflater) {
        super();
        this.mDelegate = mDelegate;
        this.mLayoutInflater = mLayoutInflater;
    }

    protected abstract View initContainer(int rootId);

    /**
     * @description
     * @author Xue
     * @createDate
     * @version 1.0
     */
    public void processViews() {
        List<Method> methods = ReflectionUtil.getMethods(mDelegate.getClass(), new ArrayList<Method>());
        List<Field> fields = ReflectionUtil.getFields(mDelegate.getClass(), new ArrayList<Field>());
        callBeforeViews(methods);
        RootLayout rootLayout = ReflectionUtil.getAnnotation(mDelegate.getClass(), RootLayout.class);
        if (rootLayout != null) {
            mRootView = initContainer(rootLayout.value());
            initLayout(fields);
            initViews(fields);
            registerClick(methods);
            callAfterViews(methods);
        }
    }

    /**
     * @description
     * @author Xue
     * @createDate
     * @version 1.0
     */
    public View prepareViews() {
        List<Method> methods = ReflectionUtil.getMethods(mDelegate.getClass(), new ArrayList<Method>());
        List<Field> fields = ReflectionUtil.getFields(mDelegate.getClass(), new ArrayList<Field>());
        callBeforeViews(methods);
        RootLayout rootLayout = ReflectionUtil.getAnnotation(mDelegate.getClass(), RootLayout.class);
        if (rootLayout != null) {
            mRootView = initContainer(rootLayout.value());
            initLayout(fields);
            initViews(fields);
            registerClick(methods);
            callAfterViews(methods);
        }
        return mRootView;
    }

    /**
     * @description
     * @author Xue
     * @createDate
     * @version 1.0
     */
    private void callBeforeViews(List<Method> methods) {
        for (Method method : methods) {
            if (method == null) {
                continue;
            }
            method.setAccessible(true);
            if (method.getAnnotation(BeforeViews.class) != null) {
                try {
                    method.invoke(mDelegate, new Object[]{});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @description 初始化VIEW
     * @author Xue
     * @createDate
     * @version 1.0
     */
    private void initViews(List<Field> fields) {
        for (Field field : fields) {
            if (field == null) {
                continue;
            }
            field.setAccessible(true);
            ViewById anno = field.getAnnotation(ViewById.class);
            if (anno != null) {
                try {
                    field.set(mDelegate, mRootView.findViewById(anno.value()));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * @description 代理回调
     * @author Xue
     * @createDate
     * @version 1.0
     */
    private void callAfterViews(List<Method> methods) {
        for (Method method : methods) {
            if (method == null) {
                continue;
            }
            method.setAccessible(true);
            if (method.getAnnotation(AfterViews.class) != null) {
                try {
                    method.invoke(mDelegate, new Object[]{});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @description 初始化布局的控件
     * @author Xue
     * @createDate
     * @version 1.0
     */
    private void initLayout(List<Field> fields) {
        for (Field field : fields) {
            if (field == null) {
                continue;
            }
            field.setAccessible(true);
            Inflater anno = field.getAnnotation(Inflater.class);
            if (anno != null) {
                try {
                    field.set(mDelegate, mLayoutInflater.inflate(anno.value(), null));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * @description 点击事件注册
     * @author Xue
     * @createDate
     * @version 1.0
     */
    private void registerClick(List<Method> methods) {
        if (mRootView == null && methods == null) {
            return;
        }
        for (final Method method : methods) {
            if (method == null) {
                continue;
            }
            method.setAccessible(true);
            Click click = method.getAnnotation(Click.class);
            if (click != null) {
                int[] viewIds = click.value();
                for (int id : viewIds) {
                    View view = mRootView.findViewById(id);
                    if (view == null) {
                        continue;
                    }
                    view.setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            try {
                                method.invoke(mDelegate, new Object[]{});
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }
    }
}
