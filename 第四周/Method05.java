package java0.customConc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier
 */
public class Method05 {
    public static void main(String[] args) {
        System.out.println("主线程开始");
        CyclicBarrier barrier = new CyclicBarrier(2);
        Thread t = new Thread(() -> {
            System.out.println("子线程开始");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                //阻塞
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("子线程结束");
        });
        t.start();
        try {
            //阻塞,并且当阻塞数量达到指定数目时同时释放
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束");
    }
}
