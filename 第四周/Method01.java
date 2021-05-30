package java0.customConc;

/**
 * 使用 join() 实现
 */
public class Method01 {
    public static void main(String[] args) {
        //主线程
        System.out.println("主线程开始执行");
        //子线程
        MyThread01 thread01 = new MyThread01();
        thread01.start();
        try {
            thread01.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程执行结束");
    }
}

class MyThread01 extends Thread {

    @Override
    public void run() {
            System.out.println("===子线程开始执行");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("===子线程结束执行");
    }

}