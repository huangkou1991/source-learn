package com.wade.springbootwebakka.config;

import akka.actor.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.wade.springbootwebakka.di.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author :lwy
 * @Date : 2018/8/24 17:25
 * @Description :
 */
@Configuration
class ApplicationConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SpringExtension springExtension;

    @Bean(destroyMethod = "shutdown")
    public ActorSystem actorSystem() {
        ActorSystem actorSystem = ActorSystem.create("demo-actor-system", akkaConfiguration());
        springExtension.initialize(applicationContext);
        return actorSystem;
    }

    @Bean
    public Config akkaConfiguration() {
        return ConfigFactory.load();
    }
}
