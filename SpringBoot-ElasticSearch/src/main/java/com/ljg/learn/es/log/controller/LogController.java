package com.ljg.learn.es.log.controller;

import com.ljg.learn.es.log.annotation.SystemLog;
import com.ljg.learn.es.log.enums.LogType;
import com.ljg.learn.es.log.service.EsLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    private EsLogService esLogService;


    @SystemLog(description = "测试", type = LogType.OPERATION)
    @GetMapping(value = "/getA")
    public ResponseEntity getA(){
        return ResponseEntity.status(200).build();
    }
}
