package com.wade.springbookakka.service;

import akka.actor.ActorPath;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.routing.SmallestMailboxPool;
import akka.util.Timeout;
import com.wade.springbookakka.akka.extension.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

import static akka.pattern.Patterns.ask;

/**
 * @Author :lwy
 * @Date : 2018/8/24 15:22
 * @Description :
 */
@Service
public class ActorService {

    @Autowired
    private ActorSystem actorSystem;

    @Autowired
    private SpringExtension springExtension;


    public ActorPath userActor() throws Exception {
        /*ActorRef routerActorRef =
                actorSystem.actorOf(springExtension.props("detectActor2").withDispatcher("detect-dispatcher")
                        .withRouter(new SmallestMailboxPool(50000)), "detectRouterActor");
        return routerActorRef.path();*/

        ActorRef userRef = actorSystem.actorOf(springExtension.props("greetingActor"));
        FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
        Timeout timeout = Timeout.durationToTimeout(duration);
        Future<Object> future = ask(userRef, new GreetingActor.Greet("John"), timeout);

        Object obj = Await.result(future, duration);

        System.out.println(obj);

        return userRef.path();
    }
}
