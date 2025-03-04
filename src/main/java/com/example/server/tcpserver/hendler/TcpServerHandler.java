package com.example.server.tcpserver.hendler;

import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.server.Storage.FileStorage;
import com.example.server.Storage.IStorage;
import com.example.server.entity.User;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


public class TcpServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private static final Logger LOGGER = Logger.getLogger(TcpServerHandler.class.getName());
    private final IStorage s = new FileStorage(".\\src\\main\\resources","Vote.json");
    private final User U = new User("");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        
        String receivedData = new String(bytes, "UTF-8"); 
        LOGGER.log(Level.INFO, "Received from client {0}: {1}", new Object[]{U.GetName(), receivedData});
        // Отправка ответа клиенту
        CommandHandler ch = new CommandHandler(s,U);
        var res = ch.HandleCommand(receivedData.split(" ")).getBytes(StandardCharsets.UTF_8);
        ctx.writeAndFlush(Unpooled.wrappedBuffer(res)); 
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.log(Level.SEVERE, "Exception caught:", cause);
        ctx.close();
    }


}
