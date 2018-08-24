package com.wade.springbootwebakka.actor;

import akka.actor.UntypedActor;
import com.wade.springbootwebakka.model.Message;
import com.wade.springbootwebakka.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @Author :lwy
 * @Date : 2018/8/24 17:36
 * @Description :
 */
@Component("workerActor")
@Scope("prototype")
public class WorkerActor extends UntypedActor {

    @Autowired
    private BusinessService businessService;

    final private CompletableFuture<Message> future;

    public WorkerActor(CompletableFuture<Message> future) {
        this.future = future;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        businessService.perform(this + " " + message);

        if (message instanceof Message) {
            future.complete((Message) message);
        } else {
            unhandled(message);
        }

        getContext().stop(self());
    }
}