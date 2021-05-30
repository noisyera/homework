package java0.customConc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Future
 */
public class Method06 {
    public static void main(String[] args) {
        System.out.println("主线程开始");
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Thread t = new Thread(() -> {
            System.out.println("子线程开始");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程结束");
        });
        //子线程启动
        Future future = executorService.submit(t);
        try {
            future.get();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        System.out.println("主线程结束");
    }
}
