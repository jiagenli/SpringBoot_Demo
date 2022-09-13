package com.ljg.consul.provider.controller;

import lombok.extern.slf4j.Slf4j;
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
}
