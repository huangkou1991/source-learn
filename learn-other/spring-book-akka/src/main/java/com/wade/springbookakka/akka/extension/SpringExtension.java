package com.wade.springbookakka.akka.extension;

import akka.actor.Extension;
import akka.actor.Props;
import com.wade.springbookakka.akka.SpringActorProducer;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Author :lwy
 * @Date : 2018/8/24 14:58
 * @Description :
 */
@Component("springExtension")
public class SpringExtension implements Extension {

    private ApplicationContext applicationContext;

    public void initialize(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Props props(String actorBeanName) {
        return Props.create(SpringActorProducer.class, applicationContext, actorBeanName);
    }
}
