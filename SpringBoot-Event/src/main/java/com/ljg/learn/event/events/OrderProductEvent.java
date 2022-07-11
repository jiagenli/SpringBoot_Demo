package com.ljg.learn.event.events;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
@Setter
@Slf4j
public class OrderProductEvent extends ApplicationEvent {
    private String orderId;

    public OrderProductEvent(Object source, String orderId) {
        super(source);
        this.orderId = orderId;
    }

    public OrderProductEvent(Object source, Clock clock) {
        super(source, clock);
    }
}
