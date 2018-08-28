package com.learn.nettyspringbootserver.handler;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author :lwy
 * @Date : 2018/8/28 11:49
 * @Description :
 */
public class HeartBeatSimpleHandle extends SimpleChannelInboundHandler<String> {
    private final static Logger LOGGER = LoggerFactory.getLogger(HeartBeatSimpleHandle.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        String result = "";
        if (msg.contains("hello")) {
            result = "hi";
        }
        ctx.writeAndFlush(result).addListener(ChannelFutureListener.CLOSE);
    }
}
