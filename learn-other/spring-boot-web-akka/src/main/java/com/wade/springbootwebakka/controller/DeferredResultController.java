package com.wade.springbootwebakka.controller;

import com.wade.springbootwebakka.model.Message;
import com.wade.springbootwebakka.service.CompletableFutureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author :lwy
 * @Date : 2018/8/24 17:40
 * @Description :
 */
@RestController
public class DeferredResultController {

    private static final Long DEFERRED_RESULT_TIMEOUT = 1000L;

    private AtomicLong id = new AtomicLong(0);

    @Autowired
    private CompletableFutureService completableFutureService;

    @RequestMapping("/async-non-blocking")
    public DeferredResult<Message> getAsyncNonBlocking() {
        DeferredResult<Message> deferred = new DeferredResult<>(DEFERRED_RESULT_TIMEOUT);
        CompletableFuture<Message> future = completableFutureService.get("async-non-blocking", id.getAndIncrement());
        future.whenComplete((result, error) -> {
            if (error != null) {
                deferred.setErrorResult(error);
            } else {
                deferred.setResult(result);
            }
        });
        return deferred;
    }
}

