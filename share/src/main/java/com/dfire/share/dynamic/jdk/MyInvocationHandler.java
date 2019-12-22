package com.dfire.share.dynamic.jdk;

import com.dfire.share.dynamic.TimeWatcher;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TimeWatcher.printIfNotFirst();
        Object result = method.invoke(target, args);
        TimeWatcher.printIfNotFirst();
        return result;
    }
}
