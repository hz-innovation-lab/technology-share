package com.dfire.share.dynamic.sta;

public class Test {

    public static void main(String[] args) {
        DemoServiceImpl demoService = new DemoServiceImpl();
        DemoProxy demoProxy = new DemoProxy(demoService);
        demoProxy.hello();
    }

}
