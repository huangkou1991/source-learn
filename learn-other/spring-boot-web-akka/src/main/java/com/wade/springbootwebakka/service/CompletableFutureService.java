package com.wade.springbootwebakka.service;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.wade.springbootwebakka.di.SpringExtension;
import com.wade.springbootwebakka.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @Author :lwy
 * @Date : 2018/8/24 17:39
 * @Description :
 */
@Service
public class CompletableFutureService {

    @Autowired
    private ActorSystem actorSystem;

    @Autowired
    private SpringExtension springExtension;

    public CompletableFuture<Message> get(String payload, Long id) {
        CompletableFuture<Message> future = new CompletableFuture<>();
        ActorRef workerActor = actorSystem.actorOf(springExtension.props("workerActor", future), "worker-actor");
        workerActor.tell(new Message(payload, id), null);
        return future;
    }
}
