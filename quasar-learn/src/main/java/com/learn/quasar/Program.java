package com.learn.quasar;

import co.paralleluniverse.common.util.Exceptions;
import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.FiberUtil;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.fibers.Suspendable;
import co.paralleluniverse.strands.Strand;

import java.util.concurrent.ExecutionException;

/**
 * @author :lwy
 * @date 2018/8/3 14:33
 */
public class Program {

    public static void main(String[] args)
            throws ExecutionException, InterruptedException {
        final Commands c = new Commands();
        FiberUtil.runInFiber(c::mySuspendableMethod1);
    }


    private static class Commands {
        private void mySuspendableMethod1()
                throws SuspendExecution, InterruptedException {
            myMarkedSyncMethod();
            myMarkedThreadBlockingMethod();
            myUnmarkedSuspendableMethod2();
        }

        private  void myMarkedSyncMethod() //synchronized
                throws SuspendExecution, InterruptedException {
            Fiber.sleep(10);
        }

        private void myMarkedThreadBlockingMethod()
                throws SuspendExecution, InterruptedException {
            Strand.sleep(10);
        }

        @Suspendable  //3
        private void myUnmarkedSuspendableMethod2() {
            mySuspendableMethod3();
        }

        interface MyUnmarkedInterface {
            @Suspendable  //4
            void myUnmarkedSuspendableInterfaceMethod();
        }

        @Suspendable
        private void mySuspendableMethod3() {
            MyUnmarkedInterface i = init();
            i.myUnmarkedSuspendableInterfaceMethod();
        }

        private MyUnmarkedInterface init() {
            return new MyUnmarkedInterface() {
                @Override
                @Suspendable
                public void myUnmarkedSuspendableInterfaceMethod() {
                    try {
                        Fiber.sleep(10);
                    } catch (Throwable t) {
                        Exceptions.rethrow(t);
                    }
                }
            };
        }
    }
}
