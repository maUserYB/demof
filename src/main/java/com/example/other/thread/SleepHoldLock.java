package com.example.other.thread;

/**
 * @Author: maYanBo
 * @CreateTime: 2024-05-28 18:10:56
 * @Description: TODO
 * @Version: 1.0
 */
public class SleepHoldLock {
    public static void main(String[] args) {
        final Object object = new Object();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    System.out.println(Thread.currentThread().getName() + " into synchronized !");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " out to synchronized !");
                }
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
    }
}
