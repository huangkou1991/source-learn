package com.learn.akka;

import akka.Done;
import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Keep;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

/**
 * http://www.baeldung.com/akka-streams
 *
 * @author :lwy
 * @date 2018/5/30 17:23
 */
public class DataImporter {
    private final ActorSystem actorSystem;
    private final AverageRepository averageRepository = new AverageRepository();

    public DataImporter(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
    }

    public ActorSystem getActorSystem() {
        return actorSystem;
    }

    /**
     * 分隔输入字符串产生列表
     *
     * @param line
     * @return
     */
    private List<Integer> parseLine(String line) {
        String fields[] = line.split(";");
        return Arrays.stream(fields)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    /**
     * 我们的初始流程会将parseLine应用于我们的输入，以创建一个输入类型为String和输出类型为Integer的Flow：
     *
     * @return
     */
    private Flow<String, Integer, NotUsed> parseContent() {
        return Flow.of(String.class).mapConcat(this::parseLine);
    }

    /**
     * 我们有我们的分析整数流。现在，我们需要实现将所有输入元素分组为对的逻辑，并计算这些对的平均值。
     * 计算八个并行线程的平均值
     *
     * @return
     */
    private Flow<Integer, Double, NotUsed> computeAverage() {
        return Flow.of(Integer.class).grouped(2)
                .mapAsyncUnordered(8, integers ->
                        CompletableFuture.supplyAsync(() -> integers.stream()
                                .mapToDouble(value -> value).average().orElse(-1.0)));
    }

    /**
     * 该流 API是一个抽象的流畅，使我们能够组成多个流程实例来实现我们的最终处理的目标。例如，我们可以有粒度流，其中一个解析JSON，另一个正在进行一些转换，另一个正在收集一些统计信息。
     * <p>
     * 这种粒度将帮助我们创建更多可测试代码，因为我们可以独立测试每个处理步骤。
     * <p>
     * 我们创建了两个可以独立工作的流程。现在，我们想把它们组合在一起。
     * <p>
     * 首先，我们要解析我们的输入字符串，接下来，我们要计算一个元素流的平均值。
     * <p>
     * 我们可以使用via（）方法编写我们的流程：
     *
     * @return
     */
    public Flow<String, Double, NotUsed> calculateAverage() {
        return Flow.of(String.class)
                .via(parseContent())
                .via(computeAverage());
    }

    /**
     * 现在，我们要创建一个使用此方法的Sink操作来保存Flow处理的结果。为了创建我们的Sink，我们首先需要创建一个将我们的处理结果作为输入类型的Flow。
     * 我们需要用Sink.ignore（）作为第一个参数并将Keep.right（）作为第二个参数来调用toMat（），因为我们要返回处理的状态：
     *
     * @return
     */
    private Sink<Double, CompletionStage<Done>> storeAverages() {
        return Flow.of(Double.class)
                .mapAsyncUnordered(4, averageRepository::save)
                .toMat(Sink.ignore(), Keep.right());
    }

    /**
     * 我们需要做的最后一件事是从输入String创建一个Source。我们可以使用via（）方法将calculateAverage（）流应用于此源。
     * <p>
     * 然后，为了将Sink添加到处理中，我们需要调用runWith（）方法并传递我们刚刚创建的storeAverages（）Sink：
     *
     * @param content
     * @return
     */
    public CompletionStage<Done> calculateAverageForContent(String content) {
        return Source.single(content)
                .via(calculateAverage())
                .runWith(storeAverages(), ActorMaterializer.create(actorSystem))
                .whenComplete(((done, throwable) -> {
                    if (done != null) {
                        System.out.println("Import finished ");
                    } else {
                        throwable.printStackTrace();
                    }
                }));
    }
}
