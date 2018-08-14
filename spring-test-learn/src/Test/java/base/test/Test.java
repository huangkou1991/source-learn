package base.test;

import com.wade.spring.learn.service.Hello;
import com.wade.spring.learn.service.TestService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author :lwy
 * @date 2018/6/6 19:10
 */
public class Test extends TestBase {

    @Resource
    private Hello hello;

    @org.junit.Test
    public void test() {
        hello.sayHello();
    }


    @Autowired
    private TestService testService;


    @org.junit.Test
    public void testService() {
        System.out.println(testService.testService());
    }


    @org.junit.Test
    public void testNotNull() {
        Assert.assertNotNull(hello);
    }
}
