package com.example.client.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

public class ReqestParamsCommand implements ICommand{

    private final String TopicName;
    private final BufferedReader Buff;
    private final ChannelHandlerContext Ctx;

    public ReqestParamsCommand(String topicName, ChannelHandlerContext ctx){
        this.TopicName = topicName;
        this.Buff = new BufferedReader(new InputStreamReader(System.in));
        this.Ctx = ctx;
    }

    @Override
    public void Exequte() {

        String voteName = "";
        String[] variants = {};

        try {
            System.out.println("Vote name:");
            voteName = Buff.readLine();
            System.out.println("Variants:");
            variants = Buff.readLine().split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        String res = "create vote params" + " -t=" + TopicName + " -v=" + voteName + " -vs=";
        for (String v : variants) {
            res += v + "|";
        }

        Ctx.writeAndFlush(Unpooled.wrappedBuffer(res.getBytes()));
    }

}
