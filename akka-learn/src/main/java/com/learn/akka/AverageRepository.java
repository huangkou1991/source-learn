package com.learn.akka;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * @author :lwy
 * @date 2018/5/30 18:09
 */
public class AverageRepository {
    /**
     * 正如我们所提到的，到目前为止，整个流程还没有执行，因为它是懒惰的。
     * 要开始执行Flow，我们需要定义一个接收器。该接收器的操作可以，例如，将数据存入数据库，或将结果发送到某些外部Web服务。
     * @param average
     * @return
     */
    CompletionStage<Double> save(Double average) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("saving average: " + average);
            return average;
        });
    }
}
