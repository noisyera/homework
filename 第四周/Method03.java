package java0.customConc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 利用 Callable 和 FutureTask 可获取到子线程的执行结果或返回值
 */
public class Method03 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("主线程开始");
        FutureTask task01 = new FutureTask(new FutureTaskThread("Thread-1"));
        FutureTask task02 = new FutureTask(new FutureTaskThread("Thread-2"));
        new Thread(task01).start();
        new Thread(task02).start();
        //判断子线程1是否执行结束
        if (!task01.isDone()) {
            System.out.println("子线程1未执行完，主线程继续等待");
        }
        //判断子线程2是否执行结束
        if (!task02.isDone()) {
            System.out.println("子线程2未执行完，主线程继续等待");
        }
        //打印一下线程1的返回值
        System.out.println(task01.get());
        //打印一下线程2的返回值
        System.out.println(task02.get());
        System.out.println("主线程结束");
    }
}

class FutureTaskThread implements Callable {

    private String threadName;

    public FutureTaskThread(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public Object call() throws Exception {
        Thread.sleep(3000);
        return "线程" + threadName + "执行结束了";
    }
}