package com.letchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author alice
 */
public class HelloServer {


    public static void main(String[] args) {
        // 定义一对线程组
        // 主线程组 前台接待
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        // 从线程组 实际执行
        EventLoopGroup childrenGroup = new NioEventLoopGroup();

        // Netty 服务器的创建
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // 注册主从线程组
        serverBootstrap.group(parentGroup, childrenGroup)
                // 设置 channel为 Nio的方式
                .channel(NioServerSocketChannel.class)
                // 设置 Handler 处理器
                .childHandler(new HelloServerInitializer());

        try {
            // 启动服务，绑定端口后同步启动
            ChannelFuture sync = serverBootstrap.bind(8088).sync();
            //监听关闭的channel，设置为同步的方式
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            parentGroup.shutdownGracefully();
            childrenGroup.shutdownGracefully();
        }
    }
}
