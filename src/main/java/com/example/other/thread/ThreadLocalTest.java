package com.example.other.thread;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: maYanBo
 * @CreateTime: 2024-05-29 18:00:08
 * @Description: TODO
 * @Version: 1.0
 */
public class ThreadLocalTest {
    private static final ThreadLocal<Integer> THREAD_LOCAL_NUM
            = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        for (int j = 0; j < 3; j++) {
            new Thread(() -> {
                for (int i = 0; i < 5; i++) {
                    Integer num = THREAD_LOCAL_NUM.get();
                    THREAD_LOCAL_NUM.set(++num);
                    System.out.println(Thread.currentThread().getName() + ":" + THREAD_LOCAL_NUM.get());
                }
            }).start();
        }
//        new ThreadPoolExecutor()
    }
}
