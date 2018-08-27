package com.wade.springboothystrixlearn.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.wade.springboothystrixlearn.hystrix.remote.RemoteServiceTestCommand;
import com.wade.springboothystrixlearn.hystrix.remote.RemoteServiceTestSimulator;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * @Author :lwy
 * @Date : 2018/8/24 18:21
 * @Description :
 */
public class HystrixTestCase {

    @Test
    public void givenInputBobAndDefaultSettings_whenCommandExecuted_thenReturnHelloBob() {
        assertThat(new CommandHelloworld("Bob").execute(), equalTo("Hello Bob!"));
    }


    //模拟远程调用
    @Test
    public void remoteTest()
            throws InterruptedException {

        HystrixCommand.Setter config = HystrixCommand
                .Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceGroup2"));

        assertThat(new RemoteServiceTestCommand(config, new RemoteServiceTestSimulator(100)).execute(),
                equalTo("Success"));
    }

    //模拟超时的远程调用
    @Test
    public void remoteTimeoutTest() {
        HystrixCommand.Setter config = HystrixCommand
                .Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceTimeOut"));

        //设置超时机制
        HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter();
        commandProperties.withExecutionTimeoutInMilliseconds(10_000);
        config.andCommandPropertiesDefaults(commandProperties);

        /*assertThat(new RemoteServiceTestCommand(config, new RemoteServiceTestSimulator(500_000)).execute(),
                equalTo("Success"));*/
        String result = new RemoteServiceTestCommand(config, new RemoteServiceTestSimulator(500_000)).execute();
        System.out.println(result);
    }

    //有可能再一瞬间有好多线程阻塞，导致CPU飙升，其他的服务不可用等问题
    //利用HystrixCommand设置线程池的大小
    @Test
    public void remoteThreadPoolTest() {
        HystrixCommand.Setter config = HystrixCommand
                .Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceThreadPool"));
        HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter();
        commandProperties.withExecutionTimeoutInMilliseconds(10_000);
        //设置其他参数

        config.andCommandPropertiesDefaults(commandProperties)
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withMaxQueueSize(10)
                        .withCoreSize(3)
                        .withQueueSizeRejectionThreshold(10));

        assertThat(new RemoteServiceTestCommand(config, new RemoteServiceTestSimulator(500)).execute(),
                equalTo("Success"));
    }

    //在上面的测试中，我们设置了最大队列大小，核心队列大小和队列拒绝大小。当最大线程数达到10且任务队列大小达到10时，Hystrix将开始拒绝请求。

    //保护资源，使用断路器进行保护，使服务有时间重新可用

    @Test
    public void givenCircuitBreaker() throws InterruptedException {
        HystrixCommand.Setter config = HystrixCommand
                .Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceCircuitBreaker"));

        HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter();
        commandProperties.withExecutionTimeoutInMilliseconds(1000)
                .withCircuitBreakerEnabled(true)
                .withCircuitBreakerSleepWindowInMilliseconds(4000)
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                .withCircuitBreakerRequestVolumeThreshold(1);


        config.andCommandPropertiesDefaults(commandProperties)
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withMaxQueueSize(1)
                        .withCoreSize(1)
                        .withQueueSizeRejectionThreshold(1));
        assertThat(this.invokeRemoteService(config, 10_000), equalTo(null));
        assertThat(this.invokeRemoteService(config, 10_000), equalTo(null));
        assertThat(this.invokeRemoteService(config, 10_000), equalTo(null));

        Thread.sleep(5000);

        assertThat(new RemoteServiceTestCommand(config, new RemoteServiceTestSimulator(500)).execute(),
                equalTo("Success"));

        assertThat(new RemoteServiceTestCommand(config, new RemoteServiceTestSimulator(500)).execute(),
                equalTo("Success"));

        assertThat(new RemoteServiceTestCommand(config, new RemoteServiceTestSimulator(500)).execute(),
                equalTo("Success"));
    }

    public String invokeRemoteService(HystrixCommand.Setter config, int timeout)
            throws InterruptedException {

        String response = null;

        try {
            response = new RemoteServiceTestCommand(config,
                    new RemoteServiceTestSimulator(timeout)).execute();
        } catch (HystrixRuntimeException ex) {
            System.out.println("ex = " + ex);
        }

        return response;
    }

    /**
     * 有了上述设置，我们的HystrixCommand现在将在两次失败的请求后打开。即使我们将服务延迟设置为500毫秒，第三个请求甚至不会命中远程服务，Hystrix将短路并且我们的方法将返回null作为响应。
     *
     * 我们随后将添加一个Thread.sleep（5000）以超越我们设置的睡眠窗口的限制。这将导致Hystrix关闭电路，后续请求将成功流过。
     */
}
