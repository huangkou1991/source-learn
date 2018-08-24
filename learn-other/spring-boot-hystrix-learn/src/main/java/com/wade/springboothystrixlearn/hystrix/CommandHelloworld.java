package com.wade.springboothystrixlearn.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @Author :lwy
 * @Date : 2018/8/24 18:14
 * @Description :
 */
public class CommandHelloworld extends HystrixCommand<String> {
    private String name;



    public CommandHelloworld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return "Hello " + name + "!";
    }

    @Override
    protected String getFallback() {
        System.out.println("Fallback执行的逻辑。");
        return "hello.................";
    }
}
