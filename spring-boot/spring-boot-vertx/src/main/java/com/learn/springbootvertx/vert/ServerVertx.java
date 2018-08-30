package com.learn.springbootvertx.vert;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.springframework.stereotype.Component;

/**
 * @Author :lwy
 * @Date : 2018/8/30 19:13
 * @Description :
 */
@Component
public class ServerVertx extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);

        router.get("/api/articles")
                .handler(this::getAllArticlesHandler);

        vertx.createHttpServer().requestHandler(router::accept).listen(8081);
    }

    private void getAllArticlesHandler(RoutingContext routingContext) {
        vertx.eventBus()
                .<String>send(ArticleRecipientVerticle.GET_ALL_ARTICLES, "", result -> {
                    if (result.succeeded()) {
                        routingContext.response()
                                .putHeader("content-type", "application/json")
                                .setStatusCode(200)
                                .end(result.result()
                                        .body());
                    } else {
                        routingContext.response()
                                .setStatusCode(500)
                                .end();
                    }
                    System.out.println(result);

                    String out=result.result().body();
                    System.out.println(out);
                });

    }
}
