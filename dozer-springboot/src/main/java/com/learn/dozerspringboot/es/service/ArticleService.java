package com.learn.dozerspringboot.es.service;

import com.learn.dozerspringboot.es.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author :lwy
 * @date 2018/6/2 9:12
 */
public interface ArticleService {
    Article save(Article article);

    Article findOne(String id);

    Iterable<Article> findAll();

    Page<Article> findByAuthorName(String name, Pageable pageable);

    Page<Article> findByAuthorsNameUsingCustomQuery(String name, Pageable pageable);
}
