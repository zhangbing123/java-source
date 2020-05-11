package main.java.juc.synchronizedtest;

import main.java.juc.Student;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-05-11 16:20
 **/
public class SynchronizedTest {

    public static void main(String[] args) {
        Student student = new Student("张三", 24);

        new Thread(() -> student.say(),"线程1").start();
        new Thread(() -> student.testSynchronized(),"线程2").start();
        new Thread(() -> student.testNotSynchronized(),"线程3").start();
    }
}
