package com.wade.springboothystrixlearn.aop.hystrix.aspect;

import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.wade.springboothystrixlearn.hystrix.remote.RemoteServiceTestCommand;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author :lwy
 * @Date : 2018/8/27 12:52
 * @Description :
 * 说明:这里需要注意：触发了降级逻辑不一定是熔断器开启，但是熔断器开启一定会执行降级逻辑。
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

        //基础属性配置
        //HystrixCommandGroupKey：用于对不同依赖的线程池/信号区分，命令分组用于对依赖操作分组，便于统计、汇总等
        //HystrixCommandKey:从HystrixCommand源码的注释也可以看到CommandKey也用于对依赖操作统计、汇总
        //HystrixThreadPoolKey当对同一业务依赖做隔离时使用CommandGroup做区分，但是对同一依赖的不同远程调用如(一个是redis 一个是http)，可以使用HystrixThreadPoolKey做隔离区分

        //命令属性
        //(1)执行属性
        //execution.isolation.strategy:隔离策略：Thread,semaphore
        //              thread 在固定大小线程池中，以单独线程执行，并发请求数受限于线程池大小。
        //              semaphore:在调用线程中执行，通过信号量来限制并发量。
        //execution.isolation.thread.timeoutInMilliseconds :设置调用者等待命令执行的超时限制，超过此时间，HystrixCommand被标记为TIMEOUT
        //execution.timeout.enabled:是否执行超时限制
        //execution.isolation.thread.interruptOnTimeout
        //   :设置HystrixCommand的执行是否在超时发生时被中断。使用线程隔离时，是否对命令执行超时的线程调用中断（Thread.interrupt()）操作

        //execution.isolation.thread.interruptOnCancel :当HystrixCommand命令执行发生cancel事件后是否应该响应中断。 默认false

        //execution.isolation.semaphore.maxConcurrentRequests
        // 设置当使用ExecutionIsolationStrategy.SEMAPHORE时，HystrixCommand执行方法允许的最大请求数。如果达到最大并发数时，后续请求会被拒绝。
        // 信号量应该是容器（比如Tomcat）线程池一小部分，不能等于或者略小于容器线程池大小，否则起不到保护作用。

        //----------------------------------------------------------------------------------------------
        //(2)回退属性
        //下面的属性控制HystrixCommand.getFallback()执行。这些属性对ExecutionIsolationStrategy.THREAD和ExecutionIsolationStrategy.SEMAPHORE都有效。
        //fallback.isolation.semaphore.maxConcurrentRequests:
        //:设置调用线程产生的HystrixCommand.getFallback()方法的允许最大请求数目。
        // 如果达到最大并发数目，后续请求将会被拒绝，如果没有实现回退，则抛出异常。(这里需要注意一点，这个变量的命名不是很规范，它实际上对THREAD和SEMAPHORE两种隔离策略都生效)

        //fallback.enabled 默认true

        //(3)断路器（Circuit Breaker）属性配置
        //circuitBreaker.enabled 默认true
        //circuitBreaker.requestVolumeThreshold:设置在一个滚动窗口中，打开断路器的最少请求数。比如：如果值是20，在一个窗口内（比如10秒），收到19个请求，即使这19个请求都失败了，断路器也不会打开。
        // (滚动窗口时间段的长度设置见下面的metrics.rollingStats.timeInMilliseconds)

        //circuitBreaker.sleepWindowInMilliseconds:设置在断路器被打开，拒绝请求到再次尝试请求的时间间隔。
        //circuitBreaker.errorThresholdPercentage :设置打开断路器并启动回退逻辑的错误比率。 默认50%

        //(4)请求上下文属性配置
        //requestCache.enabled 是否开启缓存，默认开启
        this.config = HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                .andCommandKey(HystrixCommandKey.Factory.asKey(key))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                                .withExecutionTimeoutInMilliseconds(executionTimeout)
                                .withCircuitBreakerSleepWindowInMilliseconds(sleepWindow)
                                .withCircuitBreakerEnabled(true)
                                //窗口时间内最小的失败次数
                                .withCircuitBreakerRequestVolumeThreshold(requestThreshold)
                                //隔离级别
                                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                        /*.withExecutionIsolationSemaphoreMaxConcurrentRequests(10)*/)
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(coreThreadCount)
                        .withMaxQueueSize(queueCount)
                        .withMaximumSize(maxThreadCount));
    }


    //环绕通知
    @Around("@annotation(com.wade.springboothystrixlearn.aop.hystrix.HystrixCircuitBreaker)")
    public Object circuitBreakerAround(ProceedingJoinPoint aJoinPoint) {

        //1.同步执行
        //return new RemoteServiceCommand(config, aJoinPoint).execute();
        //2.异步执行

        //缓存注册到上下文中
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Future<String> future = new RemoteServiceCommand(config, aJoinPoint).queue();
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            context.shutdown();
        }
        return null;

        //3.1、注册观察者事件订阅 -- 事件注册前执行
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


        //hystrix缓存
        @Override
        protected String getCacheKey() {
            System.err.println("this is the cache");
            //return super.getCacheKey();

            //构建缓存
            //debug来跟踪代码
            try {
                System.err.println((String) aJoinPoint.proceed());
                return (String) aJoinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return null;
        }

        //服务降级fallback逻辑
        @Override
        protected String getFallback() {
            System.out.println("this is the fallback");
            return "this is fallback....";
        }
    }


    /**
     * (6)线程池属性配置
     * coreSize
     * 设置核心线程池的大小（这个值和ThreadPoolExecutor的coreSize的含义不一样）。
     *
     * 默认值：10
     * 默认属性：hystrix.threadpool.default.coreSize
     * 实例属性：hystrix.threadpool.HystrixThreadPoolKey.coreSize
     * 实例配置：HystrixThreadPoolProperties.Setter().withCoreSize(int Value)
     * 注解使用： @HystrixCommand(threadPoolProperties = {@HystrixProperty(name = “coreSize”,value = “10”)})
     * maximumSize
     * 1.5.9新增属性，设置线程池最大值。这个是在不开始拒绝HystrixCommand的情况下支持的最大并发数。这个属性起作用的前提是设置了allowMaximumSizeToDrivergeFromCoreSize。1.5.9之前，核心线程池大小和最大线程池大小总是相同的。
     *
     * 默认值：10
     * 默认属性：hystrix.threadpool.default.maximumSize
     * 实例属性：hystrix.threadpool.HystrixThreadPoolKey.maximumSize
     * 实例配置：HystrixThreadPoolProperties.Setter().withMaximumSize(int Value)
     * 注解使用： @HystrixCommand(threadPoolProperties = {@HystrixProperty(name = “maximumSize”,value = “10”)})
     * maxQueueSize
     * 设置BlockingQueue最大的队列值。如果设置为-1，那么使用SynchronousQueue，否则正数将会使用LinkedBlockingQueue。如果需要去除这些限制，允许队列动态变化，可以参考queueSizeRejectionThreshold属性。 修改SynchronousQueue和LinkedBlockingQueue需要重启。
     *
     * 默认值：-1
     * 默认属性：hystrix.threadpool.default.maxQueueSize
     * 实例属性：hystrix.threadpool.HystrixThreadPoolKey.maxQueueSize
     * 实例配置：HystrixThreadPoolProperties.Setter().withMaxQueueSize(int Value)
     * 注解使用： @HystrixCommand(threadPoolProperties = {@HystrixProperty(name = “maxQueueSize”,value = “10”)})
     * queueSizeRejectionThreshold
     * 设置队列拒绝的阈值—-一个人为设置的拒绝访问的最大队列值，即使当前队列元素还没达到maxQueueSize。 当将一个线程放入队列等待执行时，HystrixCommand使用该属性。注意：如果maxQueueSize设置为-1，该属性不可用。
     *
     * 默认值：5
     * 默认属性：hystrix.threadpool.default.queueSizeRejectionThreshold
     * 实例属性：hystrix.threadpool.HystrixThreadPoolKey.queueSizeRejectionThreshold
     * 实例默认的设置：HystrixThreadPoolProperties.Setter().withQueueSizeRejectionThreshold(int Value)
     * 注解使用： @HystrixCommand(threadPoolProperties = {@HystrixProperty(name = “queueSizeRejectionThreshold”,value = “5”)})
     * keepAliveTimeMinutes
     * 设置存活时间，单位分钟。如果coreSize小于maximumSize，那么该属性控制一个线程从实用完成到被释放的时间。
     *
     * 默认值：1
     * 默认属性：hystrix.threadpool.default.keepAliveTimeMinutes
     * 实例属性：hystrix.threadpool.HystrixThreadPoolKey.keepAliveTimeMinutes
     * 实例配置：HystrixThreadPoolProperties.Setter().withKeepAliveTimeMinutes(int Value)
     * 注解使用： @HystrixCommand(threadPoolProperties = {@HystrixProperty(name = “keepAliveTimeMinutes”,value = “1”)})
     * allowMaximumSizeToDivergeFromCoreSize
     * 在1.5.9中新增的属性。该属性允许maximumSize起作用。属性值可以等于或者大于coreSize值，设置coreSize小于maximumSize的线程池能够支持maximumSize的并发数，但是会将不活跃的线程返回到系统中去。（详见KeepAliveTimeMinutes）
     * * 默认值：false
     * * 默认属性：hystrix.threadpool.default.allowMaximumSizeToDivergeFromCoreSize
     * * 实例属性：hystrix.threadpool.HystrixThreadPoolKey.allowMaximumSizeToDivergeFromCoreSize
     * * 实例配置：HystrixThreadPoolProperties.Setter().withAllowMaximumSizeToDivergeFromCoreSize(boolean Value)
     */

    /**
     * (7)度量属性配置
     * PS:不知道什么原因，计量属性的配置都是放在了线程池配置里面。可能是由于线程池隔离是计量属性隔离的基准。
     *
     * metrics.rollingStats.timeInMilliseconds
     * 设置统计的滚动窗口的时间段大小。该属性是线程池保持指标时间长短。
     * * 默认值：10000（毫秒）
     * * 默认属性：hystrix.threadpool.default.metrics.rollingStats.timeInMilliseconds
     * * 实例属性：hystrix.threadpool.HystrixThreadPoolKey.metrics.rollingStats.timeInMilliseconds
     * * 实例配置：HystrixThreadPoolProperties.Setter().withMetricsRollingStatisticalWindowInMilliseconds(int Value)
     * * 注解使用： @HystrixCommand(threadPoolProperties = {@HystrixProperty(name = “metrics.rollingStats.timeInMilliseconds”,value = “10000”)})
     *
     * metrics.rollingStats.numBuckets
     * 设置滚动的统计窗口被分成的桶（bucket）的数目。注意：”metrics.rollingStats.timeInMilliseconds % metrics.rollingStats.numBuckets == 0”必须为true，否则会抛出异常。
     * * 默认值：10
     * * 可能的值：任何能被metrics.rollingStats.timeInMilliseconds整除的值。
     * * 默认属性：hystrix.threadpool.default.metrics.rollingStats.numBuckets
     * * 实例属性：hystrix.threadpool.HystrixThreadPoolProperties.metrics.rollingStats.numBuckets
     * * 实例配置：HystrixThreadPoolProperties.Setter().withMetricsRollingStatisticalWindowBuckets(int Value)
     * * 注解使用： @HystrixCommand(threadPoolProperties = {@HystrixProperty(name = “metrics.rollingStats.numBuckets”,value = “10”)})
     */
}
