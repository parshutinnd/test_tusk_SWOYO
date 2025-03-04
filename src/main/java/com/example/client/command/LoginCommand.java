package com.example.client.command;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

public class LoginCommand implements ICommand{
    private final ChannelHandlerContext Ctx;
    private final String UserName;


    public LoginCommand(ChannelHandlerContext ctx, String userName) {
        this.Ctx = ctx;
        this.UserName = userName;
    }

    @Override
    public void Exequte() {
       Ctx.writeAndFlush(Unpooled.wrappedBuffer(("login" + " -u=" + UserName).getBytes()));
    }



}
