package com.dfire.share.dynamic.cglib;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionInterceptor implements MethodInterceptor {

    private AtomicInteger ai = new AtomicInteger(1);

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        int count = ai.getAndIncrement();
        Object result = null;
        try {
            System.out.println(method.getName());
            System.out.println("事务" + count +  "开始");
            result = methodProxy.invokeSuper(obj, args);
            System.out.println("事务"+ count + "结束,提交");
        } catch (Throwable throwable) {
            System.out.println("事务"+ count + "异常,回滚");
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
