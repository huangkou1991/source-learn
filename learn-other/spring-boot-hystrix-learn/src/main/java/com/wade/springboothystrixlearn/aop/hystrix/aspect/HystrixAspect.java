package com.wade.springboothystrixlearn.aop.hystrix.aspect;

import com.netflix.hystrix.*;
import com.wade.springboothystrixlearn.hystrix.remote.RemoteServiceTestCommand;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author :lwy
 * @Date : 2018/8/27 12:52
 * @Description :
 */
@Component
@Aspect
public class HystrixAspect {


    private HystrixCommand.Setter config;


    @Value("${remoteservice.command.group.key}")
    private String groupKey;

    @Value("${remoteservice.command.key}")
    private String key;

    @Value("${remoteservice.command.execution.timeout}")
    private int executionTimeout;

    @Value("${remoteservice.command.sleepwindow}")
    private int sleepWindow;

    @Value("${remoteservice.command.threadpool.maxsize}")
    private int maxThreadCount;

    @Value("${remoteservice.command.threadpool.coresize}")
    private int coreThreadCount;

    @Value("${remoteservice.command.task.queue.size}")
    private int queueCount;

    @Value("${remoteservice.command.request.threshold}")
    private int requestThreshold;

    @PostConstruct
    private void init() {

        this.config = HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                .andCommandKey(HystrixCommandKey.Factory.asKey(key))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionTimeoutInMilliseconds(executionTimeout)
                        .withCircuitBreakerSleepWindowInMilliseconds(sleepWindow)
                        .withCircuitBreakerEnabled(true)
                        //窗口时间内最小的失败次数
                        .withCircuitBreakerRequestVolumeThreshold(requestThreshold))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(coreThreadCount)
                        .withMaxQueueSize(queueCount)
                        .withMaximumSize(maxThreadCount));
    }


    //环绕通知
    @Around("@annotation(com.wade.springboothystrixlearn.aop.hystrix.HystrixCircuitBreaker)")
    public Object circuitBreakerAround(ProceedingJoinPoint aJoinPoint) {
        return new RemoteServiceCommand(config, aJoinPoint).execute();
    }


    private class RemoteServiceCommand extends HystrixCommand<String> {

        private final ProceedingJoinPoint aJoinPoint;

        public RemoteServiceCommand(Setter setter, ProceedingJoinPoint aJoinPoint) {
            super(setter);
            this.aJoinPoint = aJoinPoint;
        }

        @Override
        protected String run() throws Exception {
            try {
                return (String) aJoinPoint.proceed();
            } catch (Throwable throwable) {
                throw new Exception(throwable);
            }
        }

        @Override
        protected String getFallback() {
            return "this is fallback....";
        }
    }
}
