package com.learn.delayqueue;

import com.google.common.primitives.Ints;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author :lwy
 * @Date : 2018/8/27 17:49
 * @Description :
 */
public class DelayObject implements Delayed {

    private String data;
    private long startTime;

    public DelayObject(String data, long startTime) {
        this.data = data;
        this.startTime = startTime;
    }


    //返回小于等于0则允许返回该对象
    @Override
    public long getDelay(TimeUnit unit) {
        //返回给定时间与关联对象的剩余延迟时间
        long diff = startTime - System.currentTimeMillis();

        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {

        return Ints.saturatedCast(this.startTime - ((DelayObject) o).startTime);
    }

    @Override
    public String toString() {
        return "DelayObject{" +
                "data='" + data + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}
