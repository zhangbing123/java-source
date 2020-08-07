package com.jdk.jvm.classLoad;

/**
 * @description: 类加载机制测试
 * @author: zhangbing
 * @create: 2020-06-11 10:24
 **/
public class Math {

    private static int initDate = 666;//类加载时，在准备阶段给静态变量赋值默认值：0，在初始化阶段给静态变量赋值指定值：666
    private int count = 100;

//    private static User user = new User();//类加载时，在准备阶段给静态变量赋值默认值：null，在初始化阶段给静态变量赋值指定值：new User()


    public int compute() {
        System.out.println(count);
        int a = 2;
        int b = 5;
        return (a + b) * 10;
    }

    public static void main(String[] args) {
        System.out.println(initDate);
        Math math = new Math();
        math.compute();
    }
}
