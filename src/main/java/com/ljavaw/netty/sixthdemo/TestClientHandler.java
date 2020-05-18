package com.ljavaw.netty.sixthdemo;

import common.protobuf.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class TestClientHandler extends SimpleChannelInboundHandler<DataInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.MyMessage msg) {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        int nextInt = new Random().nextInt(3);

        System.out.println("nextInt>>>" + nextInt);
        DataInfo.MyMessage myMessage = null;
        if (0 == nextInt) {

            myMessage = DataInfo.MyMessage.newBuilder().setDataType(DataInfo.MyMessage.DataType.PersonType).
                    setPerson(DataInfo.Person.newBuilder().
                            setName("张三").setAge(30).setAddress("朝阳").
                            build()).
                    build();
        } else if (1 == nextInt) {
            myMessage = DataInfo.MyMessage.newBuilder().setDataType(DataInfo.MyMessage.DataType.DogType).
                    setDog(DataInfo.Dog.newBuilder().
                            setName("dog").setAge(5).setGender("female").
                            build()).
                    build();
        } else if (2 == nextInt) {
            myMessage = DataInfo.MyMessage.newBuilder().setDataType(DataInfo.MyMessage.DataType.CatType).
                    setCat(DataInfo.Cat.newBuilder().
                            setName("cat").setAge(6).setCity("喵星").
                            build()).
                    build();
        }

        //触发向服务器端发送一个请求
        ctx.channel().writeAndFlush(myMessage);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

        cause.printStackTrace();
        ctx.close();
    }
}
