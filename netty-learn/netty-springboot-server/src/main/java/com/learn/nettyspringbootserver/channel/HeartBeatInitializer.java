package com.learn.nettyspringbootserver.channel;

import com.learn.nettyspringbootserver.handler.HeartBeatSimpleHandle;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Author :lwy
 * @Date : 2018/8/28 11:22
 * @Description :
 */
public class HeartBeatInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel channel) throws Exception {

        ChannelPipeline pipeline = channel.pipeline();
        //监测心跳
        ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
        pipeline.addLast(new DelimiterBasedFrameDecoder(1024, delimiter))
                .addLast(new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS))

                .addLast(new StringDecoder())
                .addLast(new StringEncoder())
                .addLast(new HeartBeatSimpleHandle());
    }
}
