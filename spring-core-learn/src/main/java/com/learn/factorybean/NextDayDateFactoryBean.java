package com.learn.factorybean;

import org.joda.time.DateTime;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author :lwy
 * @date 2018/6/18 11:29
 *  spring FactoryBean的使用
 */
public class NextDayDateFactoryBean implements FactoryBean<DateTime> {

    @Override
    public DateTime getObject() throws Exception {
        return new DateTime().plusDays(1);
    }

    @Override
    public Class<? extends DateTime> getObjectType() {
        return DateTime.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
