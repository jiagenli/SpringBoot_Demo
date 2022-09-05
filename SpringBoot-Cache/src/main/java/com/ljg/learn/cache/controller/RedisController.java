package com.ljg.learn.cache.controller;

import com.ljg.learn.cache.model.User;
import com.ljg.learn.cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class RedisController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<User> queryByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(userService.queryUserById(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestParam("userId") String userId,
                           @RequestParam("username") String username) {
        User user = User.builder()
                .userId(userId)
                .username(username)
                .build();
        userService.createUser(user);
        return ResponseEntity.ok(user);
    }
}
