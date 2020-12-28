package com.letchat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

// 自定义用户处理类
public class CustomHandle extends SimpleChannelInboundHandler<HttpObject> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) {
        // 从上下文中获取 channel
        Channel channel = channelHandlerContext.channel();
        // 如果是 HttpRequest 请求
      if(httpObject instanceof HttpRequest){
          // 打印下 channel 获取的远程地址
          System.out.println(channel.remoteAddress());
          // 定义发送的数据
          ByteBuf content = Unpooled.copiedBuffer("Hello netty ~", CharsetUtil.UTF_8);
          // 构建一个 Http response
          FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK,content);
          // 为响应增加数据类型和长度
          response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
          response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

          channelHandlerContext.writeAndFlush(response);

      }
    }
}
