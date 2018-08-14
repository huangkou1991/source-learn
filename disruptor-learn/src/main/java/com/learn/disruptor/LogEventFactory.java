package com.learn.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author :lwy
 * @date 2018/7/13 15:48
 */
public class LogEventFactory implements EventFactory<LogEvent> {
    @Override
    public LogEvent newInstance() {
        return new LogEvent();
    }
}
