package com.learn.quasar;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableCallable;
import co.paralleluniverse.strands.SuspendableRunnable;

import java.util.concurrent.ExecutionException;

/**
 * @author :lwy
 * @date 2018/8/2 11:50
 */
public class HelloWorld {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        new Fiber<Void>("Caller", new SuspendableRunnable() {
            @Override
            public void run() throws SuspendExecution, InterruptedException {
                m1();
            }
        }).start();


        Fiber<String> answer=new Fiber<String>("Callable", new SuspendableCallable<String>() {
            @Override
            public String run() throws SuspendExecution, InterruptedException {
                return new HelloWorld().getMessage();
            }
        }).start();
        System.err.println(answer);
        String message=answer.get();

        System.out.println(message);
    }

    private String getMessage() {
        return "hello world";
    }

    private static void m1() throws SuspendExecution, InterruptedException {
        String m = "m1";
        System.out.println("m1 begin");
        m = m2();
        m = m3();
        System.out.println("m1 end");
        System.out.println(m);
    }

    private static String m2() throws SuspendExecution, InterruptedException {
        return "m2";
    }

    private static String m3() throws SuspendExecution, InterruptedException {
        return "m3";
    }
}
