package com.ljg.learn.es.log.serviceimpl;

import com.ljg.learn.es.log.dao.EsLogDao;
import com.ljg.learn.es.log.log.EsLog;
import com.ljg.learn.es.log.service.EsLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class EsLogServiceImpl implements EsLogService{

    @Autowired
    private EsLogDao esLogDao;

    @Override
    public EsLog saveLog(EsLog esLog) {
        return esLogDao.save(esLog);
    }

    @Override
    public void deleteLog(String id) {
        esLogDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        esLogDao.deleteAll();
    }
}
