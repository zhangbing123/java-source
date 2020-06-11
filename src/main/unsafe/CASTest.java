package main.unsafe;

import main.juc.synchronizedtest.Student;
import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @description: cas 操作
 * @author: zhangbing
 * @create: 2020-04-07 15:25
 **/
public class CASTest {

    private static long valueOffset;

    private static Unsafe unsafe;

    private static AtomicStampedReference atomicStampedReference = null;

    static {

        //通过反射获取Unsafe类 此类中的cas原子操作实现方式主要基于cmpxchg指令实现的
        unsafe = UnsafeInstance.reflectGetUnsafe();
    }


    /**
     * ABA问题描述
     * <p>
     * 原始数据为A
     * <p>
     * 线程1 把数据 A-->B
     * <p>
     * 线程2 把数据 A-->B-->A
     * <p>
     * 两个线程同时启动，
     * 线程2在线程1之前执行完
     * 线程1可以修改成功  这就会产生ABA的问题
     * <p>
     * 可以通过版本号解决ABA的问题(例如使用AtomicStampedReference)
     *
     * @param args
     */
    public static void main(String[] args) {


        //测试CAS和ABA问题
        testCASAndABAProblem();

        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("=======================================================分割线====================================================");


        //通过AtomicStampedReference解决ABA问题
        resolveABAProblem();


    }

    private static void resolveABAProblem() {

        Student student = new Student("张三", 21);

        AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>(student.getName(), 0);

        Thread thread1 = new Thread(() -> {


            System.out.println(Thread.currentThread().getName() + "修改前，读取的学生名称为:" + atomicStampedReference.getReference());

            int stamp = atomicStampedReference.getStamp();

            try {
                //此处睡眠1秒中
                Thread.currentThread().sleep(1000);

            } catch (InterruptedException e) {

            }

            boolean b = atomicStampedReference.compareAndSet(atomicStampedReference.getReference(), "王五", stamp, stamp + 1);
            if (b) {
                System.out.println(Thread.currentThread().getName() + "修改后，读取的学生名称为:" + student.getName());
            } else {
                System.out.println(Thread.currentThread().getName() + "修改失败");
            }
        }, "线程1");


        Thread thread2 = new Thread(() -> {


            System.out.println(Thread.currentThread().getName() + "修改前，读取的学生名称为:" + atomicStampedReference.getReference());

            int stamp = atomicStampedReference.getStamp();

            boolean b = atomicStampedReference.compareAndSet(atomicStampedReference.getReference(), "李四", stamp, stamp + 1);

            if (b) {

                System.out.println(Thread.currentThread().getName() + "修改后，读取的学生名称为:" + atomicStampedReference.getReference());

                int stamp2 = atomicStampedReference.getStamp();

                //测试ABA问题   此时线程2再修改回张三，测试线程1是否能够修改成功
                if (atomicStampedReference.compareAndSet(atomicStampedReference.getReference(), "张三", stamp2, stamp2 + 1)) {

                    System.out.println((Thread.currentThread().getName() + "重新修改为:" + atomicStampedReference.getReference()));
                }

            } else {
                System.out.println(Thread.currentThread().getName() + "修改失败");
            }

        }, "线程2");

        thread1.start();
        thread2.start();


    }

    private static void testCASAndABAProblem() {
        Student student = new Student("张三", 21);

        //获取对象属性的偏移量（在内存中的位置）
        try {
            valueOffset = unsafe.objectFieldOffset(Student.class.getDeclaredField("name"));
        } catch (NoSuchFieldException e) {

        }

        Thread thread1 = new Thread(() -> {

            String name = student.getName();

            System.out.println(Thread.currentThread().getName() + "修改前，读取的学生名称为:" + name);

            try {
                //此处睡眠1秒中
                Thread.currentThread().sleep(1000);

            } catch (InterruptedException e) {

            }

            boolean b = unsafe.compareAndSwapObject(student, valueOffset, name, "王五");
            if (b) {
                System.out.println(Thread.currentThread().getName() + "修改后，读取的学生名称为:" + student.getName());
            } else {
                System.out.println(Thread.currentThread().getName() + "修改失败");
            }
        }, "线程1");


        Thread thread2 = new Thread(() -> {

            String name = student.getName();

            System.out.println(Thread.currentThread().getName() + "修改前，读取的学生名称为:" + name);

            boolean b = unsafe.compareAndSwapObject(student, valueOffset, name, "李四");

            if (b) {

                System.out.println(Thread.currentThread().getName() + "修改后，读取的学生名称为:" + student.getName());

                //测试ABA问题   此时线程2再修改回张三，测试线程1是否能够修改成功
                if (unsafe.compareAndSwapObject(student, valueOffset, student.getName(), "张三")) {

                    System.out.println((Thread.currentThread().getName() + "重新修改为:" + student.getName()));
                }

            } else {
                System.out.println(Thread.currentThread().getName() + "修改失败");
            }

        }, "线程2");

        thread1.start();
        thread2.start();

    }
}
