package com.jdk.entity;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-04-07 16:33
 **/
public class Student {

    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public synchronized void say() {
        System.out.println(name + " say hello");
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sleep 之后执行...");
    }

    public void testSynchronized() {
        System.out.println(Thread.currentThread().getName()+"可以进入当前对象的非synchronized方法");
    }

    public synchronized void testNotSynchronized(){
        System.out.println(Thread.currentThread().getName()+"只有等待上一个进入另外synchronized方法的线程执行完毕后才能执行synchronized方法");
    }
}
