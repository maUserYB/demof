package com.example.other.thread;

/**
 * @Author: maYanBo
 * @CreateTime: 2024-05-29 10:45:17
 * @Description: TODO
 * @Version: 1.0
 */
public class SynchronizedDemo {
    public void method() {
        synchronized (this) {
            System.out.println("synchronized 代码块");
        }
    }
}

