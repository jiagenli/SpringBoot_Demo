package com.ljg.learn.es.repository;

import com.ljg.learn.es.model.AncientPoetry;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 继承ElasticsearchRepository接口，该接口定义了ES的CRUD
 */
public interface ESAncientPoetryRepository extends ElasticsearchRepository<AncientPoetry, String> {

    /**
     * 关键字检索标题和内容
     * @param title
     * @param content
     * @return
     */
    List<AncientPoetry> findByTitleOrContent(String title, String content);
}
