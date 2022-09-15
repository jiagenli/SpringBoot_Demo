package com.ljg.consul.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class ConsumerController {

    // 用来发现服务的节点
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consul-test")
    public void testConsume() {
        showInstances();
        doProviderAction();
        log.info("系统调用成功");
    }

    /**
     * 查找可用实例
     */
    private void showInstances() {
        discoveryClient.getInstances("reagan").forEach(s -> {
            log.info("Host: {}, Port: {}", s.getHost(), s.getPort());
        });
    }

    /**
     * 调用服务
     */
    private void doProviderAction() {
        String url = "http://reagan/provider";
        restTemplate.getForObject(url, String.class);
    }
}
