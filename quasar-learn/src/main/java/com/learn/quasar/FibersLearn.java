package com.learn.quasar;


import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;

/**
 * @author :lwy
 * @date 2018/8/2 10:28
 */
public class FibersLearn {

    public static void main(String[] args) {

        new Fiber<String>(){
            @Override
            protected String run() throws SuspendExecution, InterruptedException {
                System.out.println("say Hello.");
                return "say Hello";
            }
        }.start();

    }
}
