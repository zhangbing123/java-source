package main.java.juc.aqs.semaphore;

public class SemapPhoreTest {

    /**
     * Semaphore可以设置共享资源票据数量
     * 例如设置为2  则表明只有两个线程可以拿到锁并执行，其他线程只有加入双向链表中并阻塞
     * 等到有线程释放资源后，队列中等待最长时间的线程才可以获取锁
     * Semaphore创建的是非公平锁  所以每个线程都有一次插队的机会  一旦插队获取资源失败  则只能去队列中排队去
     *
     * @param args
     */
    public static void main(String[] args) {
        ZBSemaphore semaphore = new ZBSemaphore(3, true);

        for (int i = 0; i < 5; i++) {
            new Thread(new TestSemapPhore(semaphore)).start();
        }
    }

    public static class TestSemapPhore implements Runnable {
        private ZBSemaphore semaphore;

        public TestSemapPhore(ZBSemaphore semaphore) {
            this.semaphore = semaphore;

        }

        @Override
        public void run() {
            try {
                semaphore.acquire();

                System.out.println(Thread.currentThread().getName() + "获取到共享资源");
                Thread.sleep(3000);
                semaphore.release();


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
