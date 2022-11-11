package com.ljg.learn.openfeign.controller;

import com.ljg.learn.openfeign.service.FeignClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FeignClientController {

    @Autowired
    private FeignClientService feignClientService;

    @GetMapping
    public void query() {
        feignClientService.query();
        log.info("######## feign 调用成功");
    }
}
