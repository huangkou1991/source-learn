package com.learn.nettyspringbootserver.handler;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
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

        LOGGER.info("received the msg: " + msg);

        String result = "";
        if (msg.contains("hello")) {
            result = "hi";
        }
        ctx.writeAndFlush(result);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.READER_IDLE) {
                LOGGER.info("已经5秒没有收到信息！");
                //向客户端发送消息
                ctx.writeAndFlush("hello").addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }
        }
        super.userEventTriggered(ctx, evt);
    }
}
