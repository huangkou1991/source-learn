package com.learn.nettyspringbootserver.server;

import com.learn.nettyspringbootserver.channel.HeartBeatInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;


/**
 * @Author :lwy
 * @Date : 2018/8/28 11:04
 * @Description :
 */
@Configuration
public class HeartBeatServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeartBeatServer.class);

    private EventLoopGroup boss = new NioEventLoopGroup();
    private EventLoopGroup work = new NioEventLoopGroup();

    @Value("${netty.server.port}")
    private int nettyPort;


    @PostConstruct
    private void initServer() throws InterruptedException {
        LOGGER.info("HeartServer is init...");
        ServerBootstrap boot = new ServerBootstrap();
        boot.group(boss, work)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(nettyPort))
                .childOption(ChannelOption.SO_KEEPALIVE, true) //长连接
                .childHandler(new HeartBeatInitializer());
        ChannelFuture future = boot.bind().sync();

        if (future.isSuccess()) {
            LOGGER.info("netty server is Available");
        }
    }

    @PreDestroy
    private void destory() {
        boss.shutdownGracefully().syncUninterruptibly();
        work.shutdownGracefully().syncUninterruptibly();
        LOGGER.info("关闭 Netty 成功");
    }
}
