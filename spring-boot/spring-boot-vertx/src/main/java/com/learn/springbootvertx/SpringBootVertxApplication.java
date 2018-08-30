package com.learn.springbootvertx;

import com.learn.springbootvertx.vert.ArticleRecipientVerticle;
import com.learn.springbootvertx.vert.ServerVertx;
import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringBootVertxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootVertxApplication.class, args);
    }

    @Autowired
    private ServerVertx serverVertx;

    @Autowired
    private ArticleRecipientVerticle articleRecipientVerticle;

    @PostConstruct
    private void initVertx() {
        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(serverVertx);
        vertx.deployVerticle(articleRecipientVerticle);
    }
}
