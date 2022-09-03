package com.ljg.learn.redis.service;

import com.ljg.learn.redis.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private static final String USER_REDIS_KEY = "user:info:";

    @Autowired
    private RedisService redisService;

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    public User queryUserById(String userId) {
        log.info("根据用户ID查用户信息");

        String userJson = redisService.get(USER_REDIS_KEY + userId);

        if (null == userJson || userJson.isEmpty()) {
            log.info("缓存中没有用户信息");
            return null;
        }

        log.info("缓存中有数据");
        return User.builder().userId(userId).userName(userJson).build();
    }

    /**
     * 创建用户信息
     * @param user
     */
    public void createUser(User user) {
        String key = USER_REDIS_KEY + user.getUserId();
        log.info("向缓存中插入用户信息, key: " + key);
        String userJson = redisService.get(key);
        if (null != userJson && !userJson.isEmpty()) {
            log.info("缓存中有用户信息");
            return;
        }
        redisService.set(key, user.getUserName());
    }
}
