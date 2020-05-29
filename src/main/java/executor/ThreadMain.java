package main.java.executor;

/**
 * @description: 子线程循环 10 次，接着主线程循环 100 次，接着又回到子线程循环 10 次，接着再回到主线程又循环 100 次，如此循环50次，试写出代码
 * @author: zhangbing
 * @create: 2020-05-13 14:22
 **/
public class ThreadMain {


    public static void main(String[] args) {
        SubThread subThread = new SubThread();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                subThread.sub();
            }
        }).start();

        for (int i = 0; i < 50; i++) {
            subThread.main();
        }


    }

    static class SubThread {

        private volatile boolean isSubThread = true;

        public synchronized void sub() {

            while (!isSubThread) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 1; i <= 10; i++) {
                System.out.println("子线程循环:" + i);
            }
            isSubThread = false;
            System.out.println("子线程执行完毕，唤醒主线程....");
            this.notify();
        }


        public synchronized void main() {
            while (isSubThread) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 1; i <= 10; i++) {
                System.out.println("主线程循环:" + i);
            }
            isSubThread = true;
            System.out.println("主线程执行完毕，唤醒子线程....");
            this.notify();
        }
    }

}
