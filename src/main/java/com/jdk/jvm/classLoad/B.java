package com.jdk.jvm.classLoad;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-06-11 11:27
 **/
public class B {

    static {
        System.out.println("*******************load B*****************");
    }

    public B(){
        System.out.println("*******************init B*****************");
    }
}
