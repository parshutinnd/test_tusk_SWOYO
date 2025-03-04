package com.example.client.server;

import java.util.Scanner;

import com.example.AServer;
import com.example.client.server.handler.YourTcpClientHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client extends AServer{
    private final String host;
    private final int port;

    public Client(String _host, int _port){
        this.host = _host;
        this.port = _port;
    }

    @Override
    public void init() throws InterruptedException{

        System.out.println("User name:");

        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();

        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new YourTcpClientHandler(userName));
                    }
                });

            // Подключение к серверу
            ChannelFuture future = bootstrap.connect(host, port).sync();

            System.out.println("Connected to server at " + host + ":" + port);

            // Ожидание закрытия соединения
            future.channel().closeFuture().sync();

        } finally {
            // Завершение работы группы потоков
            group.shutdownGracefully();
        }
    }
}
