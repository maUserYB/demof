package com.example.controller;

import com.example.common.CommonResult;
import com.example.util.RedisUtil;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequestMapping("/redis")
@RestController
public class RedisController {
    @Resource
    private RedisUtil redisUtil;

    @PostMapping("/set")
    public CommonResult<Boolean> redisSet(@RequestParam("key") String key, @RequestParam("value") String value) {
        System.out.println(key + "--" + value);
        return CommonResult.success(redisUtil.set(key, value));
    }

    @SneakyThrows
    @GetMapping("get")
    public Object redisGet(@RequestParam("key") String key) {
        System.out.println(redisUtil.get(key));
        return CommonResult.success(redisUtil.get(key));
    }

    @PostMapping("expire")
    public CommonResult expire(@RequestParam("key") String key, @RequestParam("ExpireTime") long ExpireTime) {
        return CommonResult.success(redisUtil.expire(key, ExpireTime));
    }
}