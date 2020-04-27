//package main.java.juc.aqs.blockingqueue;
//
//import java.util.Scanner;
//import java.util.concurrent.ArrayBlockingQueue;
//
///**
// * @description: 阻塞队列
// * @author: zhangbing
// * @create: 2020-04-08 13:53
// **/
//public class ArrayBlockingQueueTest {
//
//    public static void main(String[] args) {
//
//        //基于数组实现的（数组不可扩容）
//        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(1);
//
//        new Thread(() -> {
//
//            Scanner sc = new Scanner(System.in);
//            while (sc.hasNext()) {
//                String next = sc.next();
//                try {
//                    arrayBlockingQueue.put(next);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                if (next.equals("quit")) {
//                    break;
//                }
//            }
//
//        }, "线程1").start();
//
//        new Thread(() -> {
//            while (true) {
//                try {
//                    Thread.sleep(10000);
//                    String take = (String) arrayBlockingQueue.take();
//
//                    System.out.println(Thread.currentThread().getName() + "获取信息:" + take);
//
//                    if (take.equals("quit")) {
//                        break;
//                    }
//
//                } catch (InterruptedException e) {
//
//                }
//
//
//            }
//        }, "线程2").start();
//
//    }
//}
