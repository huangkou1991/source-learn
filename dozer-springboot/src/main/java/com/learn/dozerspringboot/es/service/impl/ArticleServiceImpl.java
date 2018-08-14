package com.learn.dozerspringboot.es.service.impl;

import com.learn.dozerspringboot.es.domain.Article;
import com.learn.dozerspringboot.es.repository.ArticleRepository;
import com.learn.dozerspringboot.es.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author :lwy
 * @date 2018/6/2 9:13
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article findOne(String id) {
        return articleRepository.findOne(id);
    }

    @Override
    public Iterable<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Page<Article> findByAuthorName(String name, Pageable pageable) {
        return articleRepository.findByAuthorsName(name, pageable);
    }

    @Override
    public Page<Article> findByAuthorsNameUsingCustomQuery(String name, Pageable pageable) {
        return articleRepository.findByAuthorsNameUsingCustomQuery(name,pageable);
    }
}
