package com.ljg.learn.openfeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("reagan-provider")
@Component
public interface FeignClientService {

    @GetMapping("/provider")
    String query();
}
