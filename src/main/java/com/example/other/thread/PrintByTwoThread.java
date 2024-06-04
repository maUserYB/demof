package com.example.other.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: maYanBo
 * @CreateTime: 2024-05-28 17:28:39
 * @Description: TODO
 * @Version: 1.0
 */
public class PrintByTwoThread {

    static Integer lock = Integer.parseInt("0");
    public static void main(String[] args)  {
        AtomicInteger num = new AtomicInteger(0);
        Thread thread1 = new Thread(() -> {
            while (num.get() < 101) {
                synchronized (lock){
                        System.out.println(Thread.currentThread().getName() + ":" + num.getAndIncrement());
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
            }
        }, "thread001");
        Thread thread2 = new Thread(() -> {
            while (num.get() < 100) {
                synchronized (lock){
                        System.out.println(Thread.currentThread().getName() + ":" + num.getAndIncrement());
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                }
            }
        }, "thread002");
        thread1.start();
        thread2.start();
    }
}
//
//package com.example.other.thread;
//
//        import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * @Author: maYanBo
// * @CreateTime: 2024-05-28 17:28:39
// * @Description: TODO
// * @Version: 1.0
// */
//public class PrintByTwoThread {
//    private static Object lock = new Object();
//    private static int count = 0;
//    private static final int MAX_COUNT = 100;
//
//    public static void main(String[] args) {
//        Thread thread1 = new Thread(() -> {
//            while (count <= MAX_COUNT ) {
//                synchronized (lock) {
//                    try {
//                        System.out.println(Thread.currentThread().getName() + ": " + count++);
//                        lock.notify();
//                        // 如果任务还没结束 就让出锁 自己休眠
//                        lock.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        Thread thread2 = new Thread(() -> {
//            while (count <= MAX_COUNT ) {
//                synchronized (lock) {
//                    try {
//                        System.out.println(Thread.currentThread().getName() + ": " + count++);
//                        lock.notify();
//                        // 如果任务还没结束 就让出锁 自己休眠
//                        lock.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        thread1.start();
//        thread2.start();
//    }
//}

