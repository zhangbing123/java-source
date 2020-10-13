package com.jdk.productionconsum;

import java.util.Scanner;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-10-13 17:56
 **/
public class MqTestMain {

    public static void main(String[] args) {
        CustomerMQ customerMQ = new CustomerMQ();
        new Thread(() -> {

            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                String next = sc.next();
                try {
                    customerMQ.add(next);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (next.equals("quit")) {
                    break;
                }
            }

        }, "线程1").start();

        new Thread(() -> {
            while (true) {
                try {
                    String take = (String) customerMQ.get();

                    System.out.println(Thread.currentThread().getName() + "获取信息:" + take);

                    if (take.equals("quit")) {
                        break;
                    }

                } catch (InterruptedException e) {

                }


            }
        }, "线程2").start();
    }
}
