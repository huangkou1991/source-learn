package com.learn.nettyspringbootclient.handler;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author :lwy
 * @Date : 2018/8/28 12:47
 * @Description :
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<String> {

    private final static Logger LOGGER = LoggerFactory.getLogger(EchoClientHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        LOGGER.info("客户端收到消息={}", s);
    }


    //心跳监测
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.WRITER_IDLE) {
                LOGGER.info("已经 10秒没有发送消息");
                //向服务端发送消息
                ctx.writeAndFlush("heartBeat$_").addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }
        }
        Class clz = evt.getClass();
        LOGGER.info("The event classType: " + clz);
        super.userEventTriggered(ctx, evt);
    }


}
