package com.letchat.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author alice
 * 自定义用户处理类
 */
@Component
@Slf4j
public class CustomHandle extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /**
     * 用于记录和管理所有客户端的 channel
     **/
    public static ChannelGroup usersClients =
            new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg)
            throws Exception {
        // 获取客户端传输过来的消息
        String content = msg.text();
        System.out.println("接收到的消息是：" + content);
        log.info("接收到的消息是：{}", content);
        // 添加客户端到 channel 中
        for (Channel channel : usersClients) {
            channel.writeAndFlush(new TextWebSocketFrame("[服务器在]" + LocalDateTime.now() + "接收到消息为：" + content));
        }
    }
}
