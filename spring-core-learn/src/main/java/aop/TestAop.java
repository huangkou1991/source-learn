package aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author :lwy
 * @date 2018/7/26 11:46
 */
public class TestAop {

    public static void main(String[] args) {
        ApplicationContext  context=new ClassPathXmlApplicationContext("test-aop.xml");
        TestTarget target= (TestTarget) context.getBean("testAop");
        target.test();
        System.out.println("----------------");
        target.test2();
    }
}
