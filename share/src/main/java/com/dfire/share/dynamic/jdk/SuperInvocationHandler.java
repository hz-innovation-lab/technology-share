package com.dfire.share.dynamic.jdk;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SuperInvocationHandler implements InvocationHandler {

    private Object target;

    public SuperInvocationHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getAnnotation(PrintA.class) != null) {
            System.out.println("PrintA");
        }
        if (method.getAnnotation(PrintB.class) != null) {
            System.out.println("PrintB");
        }
        Object result = method.invoke(target, args);
        return result;
    }

}
