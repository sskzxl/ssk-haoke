package com.ssk.haoke.cloud.api;

import com.ssk.haoke.cloud.portal.api.service.impl.UserLoginService;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

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

        List<String> lines = FileUtils.readLines(new File("E:\\JavaWebLearning\\HaoKe\\data.json"), "UTF-8");
        for (String line : lines) {
            System.out.println(line);
            break;
        }

    }
}