package com.example.controller;

import com.example.common.CommonResult;
import com.example.util.RedisUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequestMapping("/test")
@RestController
public class TestController {
    @Resource
    private RedisUtil redisUtil;
    /**
     * 并发测试
     * 接受并发请求，接受请求后睡眠 2 分钟阻塞线程，用于判断实例可以处理多少请求
     *
     * @param num 请求ID 表示已经收到多少个请求
     */
    @GetMapping("/test-with-sleep")
    public CommonResult testWithSleep(@RequestParam Integer num) throws InterruptedException {
        System.out.println("接受到请求: " + num);
        Thread.sleep(1000 * 60);
        System.out.println("请求执行完成");
        return CommonResult.success("OK");
    }

    /**
     * http test with no sleep
     *
     * @param num
     */
    @GetMapping("/test1")
    public CommonResult test1(@RequestParam Integer num) {
        System.out.println("接受到请求: " + num);
        System.out.println("请求执行完成");
        return CommonResult.success("OK");
    }
}
