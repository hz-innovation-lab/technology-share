package com.dfire.share.dynamic.cglib.spring;

import com.dfire.share.dynamic.cglib.JavaCoder;

import net.sf.cglib.core.DebuggingClassWriter;

import org.springframework.cglib.proxy.Enhancer;

/**
 * <p>Package:com.dfire.share.dynamic.cglib.spring</p>
 * <p>Description: </p>
 * <p>Company: com.lowan</p>
 *
 * @author wjj
 * @date 2019/12/23 5:24
 */
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
