package com.ljg.learn.event.listener;

import com.ljg.learn.event.events.MsgEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MsgListener {

    @Async
    @EventListener(MsgEvent.class)
    public void sendMsg(MsgEvent msgEvent) throws InterruptedException {
        String msgId = msgEvent.getMsgId();
        long start = System.currentTimeMillis();
        Thread.sleep(2000);
        long end = System.currentTimeMillis();
        log.info("Msg listener process completed: " + (end - start));
    }
}
