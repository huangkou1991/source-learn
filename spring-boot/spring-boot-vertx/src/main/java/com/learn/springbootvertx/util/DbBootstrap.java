package com.learn.springbootvertx.util;

import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

import com.learn.springbootvertx.entity.Article;
import com.learn.springbootvertx.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbBootstrap implements CommandLineRunner {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void run(String... arg0) throws Exception {

        IntStream.range(0, 10)
          .forEach(count -> this.articleRepository.save(new Article(new Random().nextLong(), UUID.randomUUID()
          .toString())));

    }
}
