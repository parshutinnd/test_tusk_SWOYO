package com.example.client.server.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.client.command.ChooseVariantCommand;
import com.example.client.command.LoginCommand;
import com.example.client.command.ReqestParamsCommand;
import com.example.client.entity.User;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class YourTcpClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private final BufferedReader consoleReader;
    private final User u;

    public YourTcpClientHandler(String userName) {
        consoleReader = new BufferedReader(new InputStreamReader(System.in));
        u = new User(userName);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes); // Чтение данных из буфера
        
        String receivedData = new String(bytes, "UTF-8"); // Преобразование байтов в строку (можно изменить кодировку)
        System.out.println("Received from server: \n" + receivedData);
        HandleResponse(receivedData, ctx);
        readFromConsole(ctx);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        u.SetCommand(new LoginCommand(ctx,u.GetName()));
        u.Run();
        System.out.println("Type a message and press Enter to send it to the server:");
    }

    private void readFromConsole(ChannelHandlerContext ctx) {
        try {
            String input = consoleReader.readLine();
            if (input == null || input.trim().isEmpty()) {
                return;
            }

            if(input.contains("close")) {
                ctx.close();
                return;
            }

            ctx.writeAndFlush(Unpooled.wrappedBuffer((input).getBytes()));
            System.out.println("Sent to server: " + input);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    public void HandleResponse(String input,ChannelHandlerContext ctx){
        //TODO не знаю почему после выполнения запроса консоль просит ввода
        if(input.contains("request vote params -t=")){
            u.SetCommand(new ReqestParamsCommand(input.substring(23), ctx));
            u.Run();
        }

        if(input.contains("Requested")) {
            String[] Input = input.substring(10).split("\n");
            u.SetCommand(new ChooseVariantCommand(Input,ctx));
            u.Run();
        }
    }
}