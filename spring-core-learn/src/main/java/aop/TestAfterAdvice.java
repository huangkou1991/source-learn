package aop;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @author :lwy
 * @date 2018/7/26 11:10
 */
public class TestAfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println(
                "after " + target.getClass().getSimpleName() + "." + method.getName() + "()");
    }
}
