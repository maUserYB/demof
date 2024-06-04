package com.example.util;

import com.example.entity.UserResponse;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: maYanBo
 * @CreateTime: 2024-06-01 13:44:50
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class HttpUtil {
    static RestTemplate restTemplate = new RestTemplate();

    public static <T> T getRequest(T userResponse, int num) {
        String url = "http://192.168.1.4:8080/test/test-with-sleep?num=" + num;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("name", "这是name");
//        params.add("id", "1");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.queryParams(params).build().encode().toUri();
        ResponseEntity<T> forEntity = (ResponseEntity<T>) restTemplate.getForEntity(uri, userResponse.getClass());
        return forEntity.getBody();

    }

    @SneakyThrows
    public static void main1(String[] args) {
        UserResponse userResponse = new UserResponse();
        AtomicInteger count = new AtomicInteger();
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println("get request:" + count.incrementAndGet());
//                UserResponse request = getRequest(userResponse);
            }).start();
        }
//        System.out.println(JSONObject.toJSONString(request));
    }

    public static void main(String[] args) {
        UserResponse userResponse = new UserResponse();
        for (int i = 0; i < 500; i++) {
            int num = i;
            new Thread(()->{
                try{
                    UserResponse request = getRequest(userResponse, num);
                    System.out.println(request.toString());
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }).start();
        }


//        for (int i = 0; i < 500; i++) {
//            int num = i;
//            new Thread(() -> {
//                try {
//                    URL url = new URL("http://localhost:8080/test/test-with-sleep?num=" + num);
//                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                    connection.connect();
//                    System.out.println(connection.getResponseCode());
//                    connection.disconnect();
//                } catch (Exception exception) {
//                    System.out.println(exception);
//                }
//            }).start();
//        }
    }
}
