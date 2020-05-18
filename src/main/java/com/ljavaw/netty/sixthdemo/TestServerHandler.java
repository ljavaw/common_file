package com.ljavaw.netty.sixthdemo;

import common.protobuf.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.UUID;

public class TestServerHandler extends SimpleChannelInboundHandler<DataInfo.MyMessage> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.MyMessage msg) throws Exception {

        DataInfo.MyMessage.DataType dataType = msg.getDataType();
        if(dataType == DataInfo.MyMessage.DataType.PersonType){

            System.out.println(ctx.channel().remoteAddress()
                    + ", server output1 : " + msg.getPerson().getName()
                    + ", " + msg.getPerson().getAge()
                    + ", " + msg.getPerson().getAddress());
        }else if(dataType == DataInfo.MyMessage.DataType.DogType){

            System.out.println(ctx.channel().remoteAddress()
                    + ", server output2 : " + msg.getDog().getName()
                    + ", " + msg.getDog().getAge()
                    + ", " + msg.getDog().getGender());
        }else if(dataType == DataInfo.MyMessage.DataType.CatType){

            System.out.println(ctx.channel().remoteAddress()
                    + ", server output3 : " + msg.getCat().getName()
                    + ", " + msg.getCat().getAge()
                    + ", " + msg.getCat().getCity());
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

        cause.printStackTrace();
        ctx.close();
    }
}
