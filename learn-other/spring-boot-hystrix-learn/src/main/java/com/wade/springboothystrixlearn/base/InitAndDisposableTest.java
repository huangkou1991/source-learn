package com.wade.springboothystrixlearn.base;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @Author :lwy
 * @Date : 2018/9/4 14:58
 * @Description :
 */
@Component
public class InitAndDisposableTest implements InitializingBean,DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("destory");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }
}
