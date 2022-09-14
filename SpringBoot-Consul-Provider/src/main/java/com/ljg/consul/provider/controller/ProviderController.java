package com.ljg.consul.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProviderController {

    @GetMapping("/provider")
    public void query() {
        log.info("远程调用成功" + System.currentTimeMillis());
    }

    @GetMapping("/my-health-check")
    public ResponseEntity<String> myCustomCheck() {
        String message = "Testing my healh check function";
        log.info(message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
