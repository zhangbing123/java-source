package com.jdk.juc.synchronizedtest;

import com.jdk.entity.Student;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-05-11 16:20
 **/
public class SynchronizedTest {

    public static void main(String[] args) {


        Student student = new Student("张三", 24);
//
//        new Thread(() -> student.say(),"线程1").start();
//        new Thread(() -> student.testSynchronized(),"线程2").start();
//        new Thread(() -> student.testNotSynchronized(),"线程3").start();

//        new Thread(() -> {
//            Student student = new Student("张三", 24);
//            student.say();
//        }).start();
//
//        new Thread(() -> {
//            Student student = new Student("李四", 25);
//            student.say();
//        }).start();
//
        Person personA = new Person("张三", 20);


        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                //访问静态方法
                Person.staticMethod();
            }, "线程" + i).start();
        }

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                //访问person实例的同步方法  此时锁住的是personA实例对象，
                // 此时如果再有其他线程访问personA的同步方法 是获取不到锁的
                personA.instanceMethod();
            }, "线程" + i).start();
        }

//        for (int i = 1; i <= 2; i++) {
//            new Thread(() -> {
//                //调用的方法 内部有synchronized修饰代码块
//                personA.addCount();
//            }, "线程" + i).start();
//        }

    }

    static class Person {

        private Integer count = 0;

        private String name;

        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public synchronized void instanceMethod() {
            System.out.println(Thread.currentThread().getName() + "与" + name + "在交流....");
//            try {
//                Thread.currentThread().sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + "与" + name + "交流完了");
//
//            System.out.println("======================================");

        }

        public void addCount() {
            //修饰代码块
            synchronized (this) {

                System.out.println(Thread.currentThread().getName() + "对count +1");
//                System.out.println(++count);
//
//                try {
//                    Thread.currentThread().sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName() + "操作完毕" );
            }
        }

        public synchronized static void staticMethod() {
            System.out.println(Thread.currentThread().getName() + "访问静态方法成功");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "退出静态方法");
        }
    }
}
