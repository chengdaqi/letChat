package com.letchat.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author alice
 */
@Slf4j
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();
        // websocket 基于 HTTP 协议，所以要有 Http编码器
        pipeline.addLast(new HttpServerCodec());
        // 对大文件进行支持
        pipeline.addLast(new ChunkedWriteHandler());
        // 对 HTTTPMessage 进行聚合成 FullHttpRequest 或者 FullHttpResponse
        // 几乎 Netty 编程中都会用到此 Handler
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));
        // 上面是用于支持 Http 协议
        // webSocket 服务器处理的协议，用于指定给客户端连接访问的路径：/ws
        // 本 handler 会帮助你处理一些繁重的复杂的事情
        // 会帮你处理握手动作： handshaking（close, ping, pong） ping 请求，pong 为响应
        // 对于 websocket 来讲 ， 都是以 frames 进行传输的，不同的数据类型对应的 frames 也是不同的
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        pipeline.addLast(new CustomHandle());

        log.info("客户端 {} 加入pipeline链接", pipeline.channel());
        CustomHandle.usersClients.add(pipeline.channel());
    }

}
