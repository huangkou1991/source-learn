package com.learn.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @author :lwy
 * @date 2018/7/10 9:56
 */
public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        BeanOne one = new BeanOne();

        BeanOne proxyOne;


        Pointcut pc = new ControlFlowPointcut(Test.class, "runfoo");
        Advice advice = new SimpleAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pc, advice);


        //创建BeanOne代理
        ProxyFactory pf1 = new ProxyFactory();
        pf1.addAdvisor(advisor);
        pf1.setTarget(one);
        proxyOne = (BeanOne) pf1.getProxy();


        //直接调用foo
        proxyOne.foo();
        //通过runfoo方法调用foo
        Test.runfoo(proxyOne);


    }

    public static void runfoo(BeanOne beanOne) {
        beanOne.foo();
    }
}
