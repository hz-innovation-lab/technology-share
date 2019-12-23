package com.dfire.share.dynamic.jdk;

import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {
        normal();
//        huaLiHuSao();
//        seeProxyClass();

    }

    /**
     * 用法
     */
    private static void normal(){
        MyInvocationHandler invocationHandler = new MyInvocationHandler(new JavaCoder());
        Object proxyInstance = Proxy.newProxyInstance(Test.class.getClassLoader(), JavaCoder.class.getInterfaces(), invocationHandler);
        Programmer programmer = (Programmer) proxyInstance;
        programmer.code();
    }

    private static void test(){
        MyInvocationHandler invocationHandler = new MyInvocationHandler(new JavaCoder());
        Object proxyInstance = Proxy.newProxyInstance(Test.class.getClassLoader(), JavaCoder.class.getInterfaces(), invocationHandler);
        JavaCoder javaCoder = (JavaCoder) proxyInstance;
        javaCoder.work();
    }

    private static void huaLiHuSao(){
        SuperInvocationHandler invocationHandler = new SuperInvocationHandler(new JavaCoder());
        Object proxyInstance = Proxy.newProxyInstance(Test.class.getClassLoader(), JavaCoder.class.getInterfaces(), invocationHandler);
        Programmer programmer = (Programmer) proxyInstance;
        programmer.code();
        Worker worker = (Worker) proxyInstance;
        worker.work();
    }

    private static void seeProxyClass(){
        JavaCoder javaCoder = new JavaCoder();
        ProxyUtils.generateClassFile(javaCoder.getClass(), "JavaCoderProxy");
    }

    private static void selfInvoke(){
        MyInvocationHandler invocationHandler = new MyInvocationHandler(new JavaCoder());
        Object proxyInstance = Proxy.newProxyInstance(Test.class.getClassLoader(), JavaCoder.class.getInterfaces(), invocationHandler);
        Programmer programmer = (Programmer) proxyInstance;
        programmer.hashCode();
//        programmer.dream();
    }

}
