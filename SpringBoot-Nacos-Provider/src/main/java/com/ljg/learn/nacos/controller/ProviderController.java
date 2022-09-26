package com.ljg.learn.nacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RefreshScope
public class ProviderController {

    @GetMapping("/provider")
    public String query() {
        log.info("远程调用成功" + System.currentTimeMillis());
        return "hello consumer";
    }

    @GetMapping("/my-health-check")
    public ResponseEntity<String> myCustomCheck() {
        String message = "Testing my healh check function";
        log.info(message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
