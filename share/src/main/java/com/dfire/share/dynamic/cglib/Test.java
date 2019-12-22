package com.dfire.share.dynamic.cglib;



import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

public class Test {

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, System.getProperty("user.dir"));
        test1();
    }

    private static void test1(){
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(JavaCoder.class);
        enhancer.setCallback(transactionInterceptor);
        JavaCoder javaCoder = (JavaCoder) enhancer.create();
        javaCoder.code();
    }

}
