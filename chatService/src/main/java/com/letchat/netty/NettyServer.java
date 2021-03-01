package com.letchat.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

/**
 * @author alice
 */
@Component
public class NettyServer {

    public static NettyServer getInstance() {
        return InnerNettyServer.INSTANCE;
    }

    private static class InnerNettyServer {
        static final NettyServer INSTANCE = new NettyServer();
    }

    private final ServerBootstrap serverBootstrap;

    public NettyServer() {
        NioEventLoopGroup parentGroup = new NioEventLoopGroup();

        NioEventLoopGroup childrenGroup = new NioEventLoopGroup();

        serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(parentGroup, childrenGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new NettyServerInitializer());
    }

    public void start() {
        ChannelFuture future = serverBootstrap.bind(8088);
        System.err.println("netty websocket server 启动完毕...");
    }
}
