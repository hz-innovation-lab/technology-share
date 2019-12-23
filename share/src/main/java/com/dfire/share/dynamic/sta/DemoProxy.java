package com.dfire.share.dynamic.sta;

public class DemoProxy implements DemoService {

    private DemoService proxy;

    public DemoProxy(DemoService proxy) {
        this.proxy = proxy;
    }

    @Override
    public void hello() {
        System.out.println("前置处理");
        proxy.hello();
        System.out.println("后置处理");
    }

}
