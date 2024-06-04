package com.example.aop;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.UserResponse;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: maYanBo
 * @CreateTime: 2024-05-25 22:31:45
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@Component
@Aspect
public class ApiLogAspect {

    @Pointcut("execution(* com.example.controller..LoginService.*(..))")
    public void controller() {
    }

    @Around("controller()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        log.info("aop execute start.");
        long startTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String requestURI = request.getRequestURI();
        StringBuffer requestUrl = request.getRequestURL();
        String requestParam = getMethodArgs(point);
        Object result = point.proceed();
        long endTime = System.currentTimeMillis();
        String responseParam = handlerResult(result);
        UserResponse jsonObject = JSONObject.parseObject(responseParam, UserResponse.class);
        jsonObject.setMessage("aop modified result");
        log.info("requestUrl:{} requestParam：{},responseParam:{},rtt:{}ms", requestUrl, requestParam, responseParam, endTime - startTime);
        return result;
    }

    private String getMethodArgs(JoinPoint point) {
        Object[] args = point.getArgs();
        if (args == null || args.length == 0) {
            return "";
        }
        try {
            Map<String, Object> params = new HashMap<>();
            String[] parameterNames = ((MethodSignature) point.getSignature()).getParameterNames();
            for (int i = 0; i < parameterNames.length; i++) {
                Object arg = args[i];
                // 过滤不能转换成JSON的参数
                if ((arg instanceof ServletRequest) || (arg instanceof ServletResponse)) {
                    continue;
                } else if ((arg instanceof MultipartFile)) {
                    arg = arg.toString();
                }
                params.put(parameterNames[i], arg);
            }
            return JSONObject.toJSONString(params);
        } catch (Exception e) {
            log.error("接口出入参日志打印切面处理请求参数异常", e);
        }
        return Arrays.toString(args);
    }

    /**
     * 返回结果简单处理
     * 1）把返回结果转成String，方便输出。
     * 2）返回结果太长则截取（最多3072个字符），方便展示。
     *
     * @param result 原方法调用的返回结果
     * @return 处理后的
     */
    private String handlerResult(Object result) {
        if (result == null) {
            return null;
        }
        String resultStr;
        try {
            if (result instanceof String) {
                resultStr = (String) result;
            } else {
                resultStr = JSONObject.toJSONString(result);// 如果返回结果非String类型，转换成JSON格式的字符串
            }

            if (resultStr.length() > 3072) {
                resultStr = resultStr.substring(0, 3072);
            }
        } catch (Exception e) {
            resultStr = result.toString();
            log.error("接口出入参日志打印切面处理返回参数异常", e);
        }
        return resultStr;
    }
}

