package com.jdk.unsafe;

import sun.misc.Unsafe;

/**
 * @description: 使用unsafe插入内存屏障
 * @author: zhangbing
 * @create: 2020-04-07 15:00
 **/
public class FenceTest {


    public static void main(String[] args) {
        //通过反射获取Unsafe类
        Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();

        //插入读内存屏障，禁止load操作重排序。屏障前的load操作不能被重排序到屏障后，屏障后的load操作不能被重排序到屏障前
        unsafe.loadFence();

        //插入写内存屏障，禁止store操作重排序。屏障前的store操作不能被重排序到屏障后，屏障后的store操作不能被重排序到屏障前
        unsafe.storeFence();

        //禁止load、store操作重排序
        unsafe.fullFence();

    }
}
