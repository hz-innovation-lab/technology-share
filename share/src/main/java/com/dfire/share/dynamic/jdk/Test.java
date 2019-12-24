package com.dfire.share.dynamic.jdk;

import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {
        normal();
//        huaLiHuSao();
//        seeProxyClass();
//        selfInvoke();
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

    /**
     * 看看能不能转
     */
    private static void test(){
        MyInvocationHandler invocationHandler = new MyInvocationHandler(new JavaCoder());
        Object proxyInstance = Proxy.newProxyInstance(Test.class.getClassLoader(), JavaCoder.class.getInterfaces(), invocationHandler);
        JavaCoder javaCoder = (JavaCoder) proxyInstance;
        javaCoder.work();
    }

    /**
     * 个性化的用法
     */
    private static void huaLiHuSao(){
        SuperInvocationHandler invocationHandler = new SuperInvocationHandler(new JavaCoder());
        Object proxyInstance = Proxy.newProxyInstance(Test.class.getClassLoader(), JavaCoder.class.getInterfaces(), invocationHandler);
        Programmer programmer = (Programmer) proxyInstance;
        programmer.code();
        Worker worker = (Worker) proxyInstance;
        worker.work();
    }

    /**
     * 看看代理类长啥样
     */
    private static void seeProxyClass(){
        JavaCoder javaCoder = new JavaCoder();
        ProxyUtils.generateClassFile(javaCoder.getClass(), "JavaCoderProxy");
    }

    /**
     * 方法内嵌套调用自己的方法
     */
    private static void selfInvoke(){
        MyInvocationHandler invocationHandler = new MyInvocationHandler(new JavaCoder());
        Object proxyInstance = Proxy.newProxyInstance(Test.class.getClassLoader(), JavaCoder.class.getInterfaces(), invocationHandler);
        Programmer programmer = (Programmer) proxyInstance;
        programmer.hashCode();
//        programmer.dream();
    }

}
