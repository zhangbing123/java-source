package com.jdk.jvm.classLoad;

/**
 * @description: 基类
 * @author: zhangbing
 * @create: 2020-10-13 14:54
 **/
public class BaseClass {

    private static String f1 = Log.initLog("给BaseClass类的静态变量赋值");

    static {
        System.out.println("执行BaseClass类的静态代码块");
    }


    public BaseClass() {
        System.out.println("执行BaseClass类的构造方法");
    }

    {
        System.out.println("执行BaseClass类的普通代码块");
    }

    private String f2 = Log.initLog("给BaseClass类的普通成员变量赋值");


}
