package main.executor;

/**
 * @description:
 * @author: zhangbing
 * @create: 2020-05-14 10:46
 **/
public class MulThreadMain {


    public static void main(String[] args) {
        MainTask mainTask = new MainTask();
        Thread threadC = new Thread(() -> {
            for (int k = 0; k < 10; k++) {
                mainTask.task("C", 2, 0);
            }

        }, "C");
        Thread threadB = new Thread(() -> {
            for (int k = 0; k < 10; k++) {
                mainTask.task("B", 1, 2);
            }

        }, "B");
        Thread threadA = new Thread(() -> {
            for (int k = 0; k < 10; k++) {
                mainTask.task("A", 0, 1);
            }

        }, "A");
        threadA.start();
        threadB.start();
        threadC.start();

    }

    static class MainTask {

        private int i = 0;

        public synchronized void task(String id, int k, int update) {

            while (i != k) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(id);

            i = update;

            this.notifyAll();

        }
    }
}
