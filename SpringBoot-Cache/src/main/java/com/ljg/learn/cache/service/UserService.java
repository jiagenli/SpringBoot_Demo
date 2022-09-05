package com.ljg.learn.cache.service;

import com.ljg.learn.cache.mapper.UserMapper;
import com.ljg.learn.cache.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private static final String USER_REDIS_KEY = "user:info:";

    @Autowired
    private RedisService redisService;
    @Autowired
    private UserMapper userMapper;

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
            log.info("开始查数据库");
            User dbUser = userMapper.selectById(userId);
            if (dbUser != null) {
                redisService.set(USER_REDIS_KEY + dbUser.getUserId(), dbUser.getUsername());
                return dbUser;
            } else {
                log.info("数据库中也没有缓存信息");
                return null;
            }
        }

        log.info("缓存中有数据");
        return User.builder().userId(userId).username(userJson).build();
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
        userMapper.insert(user);
        redisService.set(key, user.getUsername());
    }
}
