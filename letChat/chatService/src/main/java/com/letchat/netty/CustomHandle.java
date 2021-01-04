package com.letchat.netty;

import com.letchat.enums.MsgActionEnum;
import com.letchat.pojo.ChatMsg;
import com.letchat.utils.JsonUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author alice
 * 自定义用户处理类
 */
@Component
public class CustomHandle extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    // 用于记录和管理所有客户端的 channel
    public static ChannelGroup usersClients =
            new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg)
            throws Exception {
        // 获取客户端传输过来的消息
        String content = msg.text();
        System.out.println("接收到的消息是：" + content);

        for (Channel channel : usersClients) {
            channel.writeAndFlush(new TextWebSocketFrame("[服务器在]" + LocalDateTime.now() + "接收到消息为：" + content));
        }

        // usersClients.writeAndFlush(new TextWebSocketFrame("[服务器在]" + LocalDateTime.now() + "接收到消息为：" + content));
    }
}
