package com.wade.springboothystrixlearn.hystrix;

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
    public void givenInputBobAndDefaultSettings_whenCommandExecuted_thenReturnHelloBob(){
        assertThat(new CommandHelloworld("Bob").execute(), equalTo("Hello Bob!"));
    }
}
