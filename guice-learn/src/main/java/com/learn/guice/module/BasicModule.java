package com.learn.guice.module;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.learn.guice.Communication;
import com.learn.guice.impl.DefaultCommunicator;
import com.learn.guice.mode.CommunicationMode;
import com.learn.guice.mode.impl.CommunicationModeEmailImpl;
import com.learn.guice.mode.impl.CommunicationModeImImpl;
import com.learn.guice.mode.impl.CommunicationModeSmsImpl;

/**
 * @author :lwy
 * @date 2018/5/31 18:09
 */
public class BasicModule extends AbstractModule {
    @Override
    protected void configure() {
        try {
            bind(Communication.class).toConstructor(Communication.class.getConstructor(Boolean.class));
            bind(Boolean.class).toInstance(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        bind(DefaultCommunicator.class).annotatedWith(Names.named("AnotherCommunicator")).to(DefaultCommunicator.class).asEagerSingleton();

        bind(CommunicationMode.class).annotatedWith(Names.named("im")).to(CommunicationModeImImpl.class);
        bind(CommunicationMode.class).annotatedWith(Names.named("email")).to(CommunicationModeEmailImpl.class);
        bind(CommunicationMode.class).annotatedWith(Names.named("sms")).to(CommunicationModeSmsImpl.class);

    }
}
