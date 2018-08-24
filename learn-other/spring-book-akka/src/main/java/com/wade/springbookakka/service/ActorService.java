package com.wade.springbookakka.service;

import akka.actor.ActorPath;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.routing.SmallestMailboxPool;
import com.wade.springbookakka.akka.extension.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public ActorPath userActor() {
        /*ActorRef routerActorRef =
                actorSystem.actorOf(springExtension.props("detectActor2").withDispatcher("detect-dispatcher")
                        .withRouter(new SmallestMailboxPool(50000)), "detectRouterActor");
        return routerActorRef.path();*/

        ActorRef userRef = actorSystem.actorOf(springExtension.props("detectActor2"),"greetActor");

        return userRef.path();
    }
}
