package java0.customConc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue
 */
public class Method07 {
    public static void main(String[] args) {
        System.out.println("主线程开始");
        //数组型队列，长度为1
        BlockingQueue queue = new ArrayBlockingQueue(1);
        Thread t = new Thread(() -> {
            System.out.println("子线程开始");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                //在队列中加入数据
                queue.put("OK");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程结束");
        });
        t.start();
        try{
            //主线程在队列中获取数据，take()方法会阻塞队列，ps还有非阻塞的方法poll()
            queue.take();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("主线程结束");
    }
}
