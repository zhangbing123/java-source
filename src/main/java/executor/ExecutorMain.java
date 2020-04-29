package main.java.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-04-27 10:21
 **/
public class ExecutorMain {

    public static void main(String[] args) {

//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("123"+Thread.currentThread().getName());
//            }
//        },"test");
//        thread.start();
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(() -> System.out.println("1："+Thread.currentThread().getName()));
        service.execute(() -> System.out.println("2："+Thread.currentThread().getName()));
        service.execute(() -> System.out.println("3："+Thread.currentThread().getName()));
        service.execute(() -> System.out.println("4："+Thread.currentThread().getName()));
        service.shutdown();
    }
}
