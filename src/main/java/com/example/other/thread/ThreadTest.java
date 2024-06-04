package com.example.other.thread;

import com.example.entity.UserInfo;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: maYanBo
 * @CreateTime: 2024-05-28 16:29:40
 * @Description: 死锁
 * @Version: 1.0
 */
public class ThreadTest {
    private UserInfo resource1 = new UserInfo();
    private UserInfo resource2 = new UserInfo();

    public static void main(String[] args) throws InterruptedException {
        ThreadTest threadTest = new ThreadTest();
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread thread001 = new Thread(() -> {
            synchronized (threadTest.resource1){
                System.out.println("thread001 resource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    threadTest.resource1.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (threadTest.resource2){
                    System.out.println("thread001 resource2");
                }
            }
            countDownLatch.countDown();
        }, "thread001");
        Thread thread002 = new Thread(() -> {
            synchronized (threadTest.resource2){
                System.out.println("thread002 resource2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (threadTest.resource1){
                    System.out.println("thread002 resource1");
                    threadTest.resource1.notify();
                }
            }
            countDownLatch.countDown();
        }, "thread002");
        thread001.start();
        thread002.start();
        countDownLatch.await();
        System.out.println("end.");
    }
}
