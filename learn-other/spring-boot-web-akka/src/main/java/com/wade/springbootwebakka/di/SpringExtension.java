package com.wade.springbootwebakka.di;

import akka.actor.Extension;
import akka.actor.Props;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Author :lwy
 * @Date : 2018/8/24 17:27
 * @Description :
 */
@Component
public class SpringExtension  implements Extension {

    private ApplicationContext applicationContext;

    public void initialize(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Props props(String actorBeanName) {
        return Props.create(SpringActorProducer.class, applicationContext, actorBeanName);
    }

    public Props props(String actorBeanName, Object... args) {
        return Props.create(SpringActorProducer.class, applicationContext, actorBeanName, args);
    }
}
