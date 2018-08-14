package aop;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import java.lang.reflect.Method;

/**
 * @author :lwy
 * @date 2018/7/26 11:06
 */
public class TestPointCut implements Pointcut {
    @Override
    public ClassFilter getClassFilter() {
        return ClassFilter.TRUE;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                if (method.getName().equals("test")) {
                    return true;
                }
                return false;
            }

            @Override
            public boolean isRuntime() {
                return true;
            }

            //动态
            @Override
            public boolean matches(Method method, Class<?> targetClass, Object[] args) {

                //还可以对目标类进行过滤
                if (method.getName().equals("test")) {
                    return true;
                }
                return false;
            }
        };
    }
}
