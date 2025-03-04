package com.example.client.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

public class ChooseVariantCommand implements ICommand{

    private final String[] Variants;
    private final BufferedReader Buff;
    private final ChannelHandlerContext Ctx;

    public ChooseVariantCommand(String[] variants, ChannelHandlerContext ctx){
        this.Variants = variants;
        this.Buff = new BufferedReader(new InputStreamReader(System.in));
        this.Ctx = ctx;
    }

    @Override
    public void Exequte() {

        String VariantName = "";
        String res = "";

        System.out.println("Write an option name");

        try {
            VariantName = Buff.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 2; i < Variants.length; i++) {
            if (Variants[i].equals(VariantName)){
                res = "vote params response" + " -t=" + Variants[0] + " -v=" + Variants[1] + " -vs=" + Variants[i];
                break;
            }
        }
        if (res.equals("")){
            res = "Variant not found";
        }

        Ctx.writeAndFlush(Unpooled.wrappedBuffer(res.getBytes()));

    }

}
