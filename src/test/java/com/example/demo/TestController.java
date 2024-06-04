package com.example.demo;

import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpRequest;

/**
 * @Author: maYanBo
 * @CreateTime: 2024-05-31 19:15:16
 * @Description: TODO
 * @Version: 1.0
 */
public class TestController {

    /**
     * 为了方便测试直接起多线程并发请求测试 当然也可以用 jmeter 等压测工具
     */
    @Test
    public void test1() {
        for (int i = 0; i < 1; i++) {
            int num = i;
            new Thread(() -> {
                try {
                    System.out.println("adsfasfsa");
//                    URL url = new URL("http://localhost:8080/test/test1?num=" + num);
//                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                    connection.connect();
//                    System.out.println(connection.getResponseCode());
//                    connection.disconnect();
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }).start();
        }
    }
}
