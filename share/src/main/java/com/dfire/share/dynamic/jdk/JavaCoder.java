package com.dfire.share.dynamic.jdk;


public class JavaCoder implements Programmer,Worker{

    public void code() {
        System.out.println("code using java");
    }

    @Override
    public void dream() {
        System.out.println(this.hashCode());
        System.out.println("dream of java");
    }

    public void work() {
        System.out.println("work using java");
    }
}
