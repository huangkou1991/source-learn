package com.wade.springbookakka.akka.config;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.wade.springbookakka.akka.extension.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author :lwy
 * @Date : 2018/8/24 15:02
 * @Description :
 */
@Configuration
public class AkkaConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SpringExtension springExtension;

    @Bean
    public ActorSystem actorSystem(){
        ActorSystem system=ActorSystem.create("ActorSystem");
        springExtension.initialize(applicationContext);
        return system;
    }

    @Bean
    public Config akkaConfiguration(){
        return ConfigFactory.load();
    }
}
