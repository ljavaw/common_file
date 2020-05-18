package com.ljavaw.netty.seconddemo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.time.LocalDateTime;

public class MyClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {

        System.out.println(ctx.channel().remoteAddress() + ", client output :" + msg);
        ctx.writeAndFlush("from client " + LocalDateTime.now());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        //触发向服务器端发送一个请求
        ctx.channel().writeAndFlush("来自客户端的问候！");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

        cause.printStackTrace();
        ctx.close();
    }
}
