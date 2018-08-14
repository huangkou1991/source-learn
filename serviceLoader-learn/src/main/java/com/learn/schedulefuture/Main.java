package com.learn.schedulefuture;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author :lwy
 * @date 2018/7/12 18:09
 * https://blog.csdn.net/paincupid/article/details/52029319
 */
public class Main {
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

        /*
         * Create a MyScheduledThreadPool object
         */
        MyScheduledThreadPoolExecutor executor=new MyScheduledThreadPoolExecutor(2);

        /*
         * Create a task object
         */
        Task task=new Task();

        /*
         * Write the start date of the execution
         */
        System.out.printf("Main: %s\n",new Date());

        /*
         * Send to the executor a delayed task. It will be executed after 1 second of delay
         */
        executor.schedule(task, 1, TimeUnit.SECONDS);

        /*
         * Sleeps the thread three seconds
         */
        TimeUnit.SECONDS.sleep(3);

        /*
         * Create another task
         */
        task=new Task();

        /*
         * Write the actual date again
         */
        System.out.printf("Main: %s\n",new Date());

        /*
         * Send to the executor a delayed task. It will begin its execution after 1 second of dealy
         * and then it will be executed every three seconds
         */
        executor.scheduleAtFixedRate(task, 1, 3, TimeUnit.SECONDS);

        /*
         * Sleep the thread during ten seconds
         */
        TimeUnit.SECONDS.sleep(10);

        /*
         * Shutdown the executor
         */
        executor.shutdown();

        /*
         * Wait for the finalization of the executor
         */
        executor.awaitTermination(1, TimeUnit.DAYS);

        /*
         * Write a message indicating the end of the program
         */
        System.out.printf("Main: End of the program.\n");
    }
}
