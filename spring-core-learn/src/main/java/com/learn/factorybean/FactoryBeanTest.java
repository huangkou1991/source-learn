package com.learn.factorybean;

import org.joda.time.DateTime;

/**
 * @author :lwy
 * @date 2018/6/18 11:34
 */
public class FactoryBeanTest {

    //没有申明为NextDayDateFactoryBean
    private DateTime nextDayDateFactoryBean;

    public DateTime getNextDayDateFactoryBean() {
        return nextDayDateFactoryBean;
    }

    public void setNextDayDateFactoryBean(DateTime nextDayDateFactoryBean) {
        this.nextDayDateFactoryBean = nextDayDateFactoryBean;
    }
}
