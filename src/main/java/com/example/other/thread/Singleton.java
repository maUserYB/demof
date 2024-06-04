package com.example.other.thread;

import java.security.Signature;

/**
 * @Author: maYanBo
 * @CreateTime: 2024-05-29 10:30:44
 * @Description: 线程安全的单例模式
 * @Version: 1.0
 */
public class Singleton {
    private volatile static Singleton singleton;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (singleton) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
