package com.jdk.jvm.classLoad;

/**
 * @description: 派生类
 * @author: zhangbing
 * @create: 2020-10-13 14:54
 **/
public class DeriveClass extends BaseClass {

    private static String d1 = Log.initLog("给DeriveClass类的静态变量赋值");
    private String d2 = Log.initLog("给DeriveClass类的普通成员变量赋值");

    static {
        System.out.println("执行DeriveClass类的静态代码块");
    }

    {
        System.out.println("执行DeriveClass类的普通代码块");
    }

    public DeriveClass() {
        System.out.println("执行DeriveClass类的构造方法");
    }
}
