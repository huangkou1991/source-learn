package com.learn.nettyspringbootclient.init;

import com.learn.nettyspringbootclient.handler.EchoClientHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @Author :lwy
 * @Date : 2018/8/28 12:39
 * @Description :
 */
public class CustomerHandleInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline()
                //10 秒没发送消息 将IdleStateHandler 添加到 ChannelPipeline 中
                .addLast(new IdleStateHandler(0, 10, 0))
                //解决粘包，拆包
                .addLast(new LineBasedFrameDecoder(1024))
                .addLast(new StringDecoder())
                .addLast(new StringEncoder())
                .addLast(new EchoClientHandler())
        ;
    }
}
