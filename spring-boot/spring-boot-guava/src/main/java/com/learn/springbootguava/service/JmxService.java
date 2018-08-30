package com.learn.springbootguava.service;

import com.learn.springbootguava.meta.ClassAndMethod;
import com.learn.springbootguava.meta.Stacktrace;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author :lwy
 * @Date : 2018/8/30 17:15
 * @Description :
 */

public class JmxService {

    private ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

    public void getStackTrace() {
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        if (threadInfos == null) {
            return;
        }

        List<Stacktrace> stacktraceList = new ArrayList<>();
        Arrays.stream(threadInfos).forEach(threadInfo -> {
            String threadName = threadInfo.getThreadName();
            if (StringUtils.isEmpty(threadName)) {
                threadName = "";
            }

            StackTraceElement[] stackTraces = threadInfo.getStackTrace();
            if (CollectionUtils.isEmpty(Arrays.asList(stackTraces))) {
                return;
            }

            Stacktrace stacktrace = new Stacktrace();
            stacktrace.setThreadName(threadName);
            stacktrace.setThreadState(String.valueOf(threadInfo.getThreadState()));

            int totalLength = 0;
            List<ClassAndMethod> stack = new ArrayList<>(stackTraces.length);
            for (int i = stackTraces.length - 1; i >= 0; i--) {
                StackTraceElement stackTraceElement = stackTraces[i];
                String className = String.valueOf(stackTraceElement.getClassName());
                String methodName = String.valueOf(stackTraceElement.getMethodName());
                stack.add(new ClassAndMethod(className, methodName));

                totalLength += className.length() + methodName.length();

                if (totalLength >= 20000) {
                    stack.add(new ClassAndMethod("_stack_", "_trimmed_"));
                    break;
                }
            }
            // Reverse the stack so the top method (most nested method) is the first element of the array
            ClassAndMethod[] classAndMethodArray = new ClassAndMethod[stack.size()];
            for (int i = 0; i < stack.size(); i++) {
                classAndMethodArray[classAndMethodArray.length - 1 - i] = stack.get(i);
            }

            stacktrace.setStack(classAndMethodArray);

            stacktraceList.add(stacktrace);
        });
        System.out.println(stacktraceList);
    }
}
