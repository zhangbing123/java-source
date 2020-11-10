package com.jdk.executor.zb;

import com.jdk.entity.Student;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-05-06 11:12
 **/
public class ZbExecutorTest {

    public static void main(String[] args) throws InterruptedException {

//        ZbThreadPoolExecutor zbThreadPoolExecutor = new ZbThreadPoolExecutor(3, 40, 5, 0);
//        try {
//            for (int i = 0; i < 15; i++) {
//                int finalI = i;
//                zbThreadPoolExecutor.execute(() -> System.out.println(finalI + ":" + Thread.currentThread().getName()));
//            }
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("123");
//            zbThreadPoolExecutor.execute(() -> System.out.println("当前执行任务的线程为:" + Thread.currentThread().getName()));
//        } finally {
//            zbThreadPoolExecutor.shutdown();
//        }

        Student student = new Student();

        student.setAge(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    int age = student.getAge();
                    System.out.println(age);
                    if (age!=1){
                        break;
                    }

                }
            }
        }).start();

        Thread.currentThread().sleep(10);

        new Thread(new Runnable() {
            @Override
            public void run() {
                student.setAge(2);
            }
        }).start();

    }
}
