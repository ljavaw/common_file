package com.ljavaw.netty.sixthdemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * protobuf格式传输
 * 1.创建DataInfo.proto文件，生成java类，在ProtoBufTest类中进行测试
 * 2.传输单个对象
 * 3.传输多个对象（通过修改DataInfo.proto，增加枚举类进行实现）
 * 4.客户端和服务端共用的DataInfo类实现git托管，远程调用
 */
public class TestClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new TestClientInitializer());

            ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
