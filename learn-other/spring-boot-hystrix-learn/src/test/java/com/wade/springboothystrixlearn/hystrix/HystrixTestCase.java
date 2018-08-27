package com.wade.springboothystrixlearn.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
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
}
