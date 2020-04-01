package com.ssk.haoke.cloud.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssk.haoke.cloud.portal.api.service.impl.HouseResourcesServiceImpl;
import com.ssk.haoke.cloud.portal.api.service.impl.UserLoginService;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.util.Set;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TestRedis {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
//    @Autowired
//    private HttpClient httpClient;
    @Autowired
    private HouseResourcesServiceImpl houseResourcesService;
    @Test
    public void testSave() {

        for (int i = 0; i < 100; i++) {
            this.redisTemplate.opsForValue().set("key_" + i, "value_" + i);
        }
        Set<String> keys = this.redisTemplate.keys("key_*");
        for (String key : keys) {
            String value = this.redisTemplate.opsForValue().get(key);
            System.out.println(value);
//           this.redisTemplate.delete(key);
        }
    }

    @Test
    public void testUserMapper(){
        UserLoginService userService = new UserLoginService();
//        String s = userService.checkLogin("ssk", "123456");
//        System.out.println(s);

    }
    @Test
    public void insertData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CloseableHttpClient httpClient =  HttpClientBuilder.create().build();
        String address = "广州";
        HttpGet httpGet = new HttpGet("http://api.map.baidu.com/geocoder/v2/?address=" + address + "&ak=jpfEH2etB2gutGyHpxVdWy8ZrTxbu0qj&output=json");
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                String string = EntityUtils.toString(responseEntity);

            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {


    }
}