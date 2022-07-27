package com.ljg.learn.es.log.dao;

import com.ljg.learn.es.log.log.EsLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * ES log 的 DAO 接口
 */
public interface EsLogDao extends ElasticsearchRepository<EsLog, String> {
    /**
     * 通过类型获取
     * @param Type
     * @param pageable
     * @return
     */
    Page<EsLog> findByLogType(Integer Type, Pageable pageable);
}
