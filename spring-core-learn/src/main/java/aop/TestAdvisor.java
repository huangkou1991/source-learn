package aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;

/**
 * @author :lwy
 * @date 2018/7/26 11:12
 * 整合advice与pointCut
 */
public class TestAdvisor implements PointcutAdvisor {
    @Override
    public Pointcut getPointcut() {
        return new TestPointCut();
    }

    @Override
    public Advice getAdvice() {
        return new TestAfterAdvice();
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }
}
