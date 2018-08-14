package com.learn.springbootlearn.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author :lwy
 * @date 2018/7/31 18:10
 */
@Component
@Aspect
public class MethodAspect {
    private Map<String, Integer> methodCount = new ConcurrentHashMap();

    private Map<String, List<Integer>> methodCost = new ConcurrentHashMap();


    @Around("@annotation(com.learn.springbootlearn.aop.MethodAnnotation)")
    public Object process(ProceedingJoinPoint joinPoint) {
        Object obj = null;
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = className + "_" + getMethodName(joinPoint);

        long startTime = System.currentTimeMillis();
        try {
            obj = joinPoint.proceed();
        } catch (Throwable t) {
            System.err.println(t);
        } finally {
            long costTime = System.currentTimeMillis() - startTime;
            System.err.println("method={}, cost_time={}" + methodName + costTime);
            methodCount.put(methodName, methodCount.getOrDefault(methodName, 0) + 1);
            List<Integer> costList = methodCost.getOrDefault(methodName, new ArrayList<>());
            costList.add((int) costTime);
            methodCost.put(methodName, costList);
        }
        return obj;
    }

    private String getMethodName(ProceedingJoinPoint joinPoint) {

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        return method.getName();
    }

}
