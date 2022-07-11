package com.ljg.learn.event.listener;

import com.ljg.learn.event.events.OrderProductEvent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 这个例子说明可以用
 * 20220711 第一次执行时，listener没有反应，原因是没有加@Component，bean没有被Spring管理
 */
@Slf4j
@Component
public class OrderProductListener implements ApplicationListener<OrderProductEvent> {
    @SneakyThrows
    @Override
    public void onApplicationEvent(OrderProductEvent event) {
        String orderId = event.getOrderId();
        long start = System.currentTimeMillis();
        Thread.sleep(2000);
        log.info("get event: " + orderId);
        long end = System.currentTimeMillis();
        log.info("OrderProductListener process uses time: " + (end - start));
    }
}
