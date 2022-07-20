package com.ljg.learn.es.service;

import com.ljg.learn.es.model.AncientPoetry;
import com.ljg.learn.es.repository.ESAncientPoetryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ESAncientPoetryService {

    private final ESAncientPoetryRepository esAncientPoetryRepository;

    public boolean addAncientPoetry(AncientPoetry ancientPoetry) {
        esAncientPoetryRepository.save(ancientPoetry);
        return Boolean.TRUE;
    }

    public AncientPoetry getById(String id) {
        Optional<AncientPoetry> ancientPoetryOptional = esAncientPoetryRepository.findById(id);
        return ancientPoetryOptional.orElse(null);
    }

    /**
     * 按照关键字检索 标题 内容
     * @param keyword
     * @return
     */
    public List<AncientPoetry> findAncientPoetry(String keyword) {
        return esAncientPoetryRepository.findByTitleOrContent(keyword, keyword);
    }
}
