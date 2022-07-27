package com.ljg.learn.es.log.service;

import com.ljg.learn.es.log.log.EsLog;

public interface EsLogService {

    EsLog saveLog(EsLog esLog);

    void deleteLog(String id);

    void deleteAll();

}
