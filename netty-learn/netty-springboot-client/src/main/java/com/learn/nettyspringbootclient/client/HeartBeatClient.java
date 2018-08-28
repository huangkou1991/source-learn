package com.learn.nettyspringbootclient.client;

import com.learn.nettyspringbootclient.init.CustomerHandleInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author :lwy
 * @Date : 2018/8/28 12:34
 * @Description :
 */
@Configuration
public class HeartBeatClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeartBeatClient.class);

    private EventLoopGroup work = new NioEventLoopGroup();

    @Value("${netty.server.port}")
    private int nettyPort;

    @Value("${netty.server.host}")
    private String host;

    private SocketChannel channel;

    @PostConstruct
    private void initClient() throws InterruptedException {
        LOGGER.info("init the netty client");
        Bootstrap boot = new Bootstrap();
        boot.group(work)
                .channel(NioSocketChannel.class)
                .handler(new CustomerHandleInitializer());

        ChannelFuture future = boot.connect(host, nettyPort).sync();
        if (future.isSuccess()) {
            LOGGER.info("netty client is succeed");
        }

        channel = (SocketChannel) future.channel();
    }

    @PreDestroy
    private void destory() {
        work.shutdownGracefully().syncUninterruptibly();
        LOGGER.info("关闭 Netty 成功");
    }


    /**
     * 发送消息
     *
     * @param msg
     */
    public void sendMsg(String msg) {
        ChannelFuture future = channel.writeAndFlush(msg);
        future.addListener(channelFuture -> {
            LOGGER.info("发送消息成功。");
        });
    }
}
