package com.ljg.learn.nacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class TestController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/echo")
    public String echo() {
        log.info("调用nacos");
        // 下面这个一直报Request URI does not contain a valid hostname
        // 原因是reagan_provider不能有下划线
        return restTemplate.getForObject("http://reagan-provider/provider", String.class);
    }
}
