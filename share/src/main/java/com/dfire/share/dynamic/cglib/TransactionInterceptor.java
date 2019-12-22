package com.dfire.share.dynamic.cglib;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TransactionInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        try {
            System.out.println("事务开始");
            result = methodProxy.invokeSuper(obj, args);
            System.out.println("事务结束,提交");
        } catch (Throwable throwable) {
            System.out.println("事务异常,回滚");
            throw throwable;
        }
        return result;
    }
/*
    @Override
    public Object intercept(Object obj, Method method, Object[] args,
                            MethodProxy methodProxy) throws Throwable {
        System.out.println("**** 城府");
        methodProxy.invokeSuper(obj, args);
//        method.invoke(obj,args);
        System.out.println("**** 城府完");
        return null;
    }*/
}
