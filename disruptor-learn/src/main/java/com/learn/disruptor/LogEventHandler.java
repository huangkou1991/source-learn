package com.learn.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @author :lwy
 * @date 2018/7/13 15:49
 */
public class LogEventHandler implements EventHandler<LogEvent> {
    @Override
    public void onEvent(LogEvent logEvent, long l, boolean b) throws Exception {
        System.out.println(logEvent.getMessage());
    }
}
