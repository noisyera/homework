package java0.customConc;

import java.util.concurrent.CountDownLatch;

/**
 * 使用 countDownLatch 实现
 */
public class Method02 {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);

        System.out.println("主线程开始");
        MyThread02 thread02 = new MyThread02("Thread-01", latch);
        thread02.start();
//        MyThread02 thread021 = new MyThread02("Thread-02", latch);
//        thread021.start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束");

    }
}

class MyThread02 extends Thread {

    private String threadName;

    private CountDownLatch latch;

    public MyThread02(String threadName, CountDownLatch latch) {
        this.threadName = threadName;
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("线程" + threadName + "开始");
        try {
            //模拟线程工作
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程" + threadName + "结束");
        latch.countDown();
    }

}