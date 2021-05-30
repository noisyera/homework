package java0.customConc;

/**
 * wait() 和 notify()
 */
public class Method04 {
    public static void main(String[] args) {
        System.out.println("主线程开始");
        Object lock = new Object();
        Thread t = new Thread(() -> {
            System.out.println("子线程开始");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //获取对象锁
            synchronized (lock) {
                //子线程唤醒
                lock.notify();
            }
            System.out.println("子线程结束");
        });

        //启动子线程
        t.start();
        try {
            //获取对象锁
            synchronized (lock) {
                //主线程等待
                lock.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束");
    }
}
