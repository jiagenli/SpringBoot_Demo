package com.ljg.learn.event.service;

import com.ljg.learn.event.events.MsgEvent;
import com.ljg.learn.event.events.OrderProductEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * event 流程
 * 1. 定义事件，继承ApplicationEvent
 * 2. 定义Listener, 实现ApplicationListener接口
 * 3. 定义发布者，发布者需要实现ApplicationEventPublisher接口
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    // 注入ApplicationContext来发布事件
    private final ApplicationContext applicationContext;

    public String buyOrder(String orderId) {
        long start = System.currentTimeMillis();
        // 同步处理事件，发布事件后，Listener对象处理事件
        applicationContext.publishEvent(new OrderProductEvent(this, orderId));
        // 异步处理测试
        applicationContext.publishEvent(new MsgEvent(orderId));

        long end = System.currentTimeMillis();
        log.info("all tasks completed, total used time: " + (end - start));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "buy success";
    }
}
