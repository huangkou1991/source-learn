package com.learn.springbootasync.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author :lwy
 * @Date : 2018/9/30 10:06
 * @Description :
 */
public class BackgroundTask extends Thread {

    private static final Logger logger=LoggerFactory.getLogger();

    private volatile boolean isPack = false;

    @Override
    public void run() {
        int indexCount=0;
        logger.info("成功启动审核任务监控工作线程，当前工作线程每次unpack连续工作的时间设定为"+maxWorkCount+"秒");
        while(true){

            if(isPack){

            }
        }

    }
}
