package com.learn.springbootvertx.repository;


import com.learn.springbootvertx.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
