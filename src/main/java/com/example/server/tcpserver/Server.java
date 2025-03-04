package com.example.server.tcpserver;


import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.AServer;
import com.example.server.tcpserver.hendler.TcpServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server extends AServer{

    private static final Logger LOGGER = Logger.getLogger(TcpServerHandler.class.getName());
    
    private final int port;

    public Server(int port){this.port=port;}

    @Override
    public void init() throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup(); // Группа потоков для приема новых соединений
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // Группа потоков для обработки входящих запросов

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .childHandler(new ChannelInitializer<SocketChannel>() { // Обработчик событий канала
                 @Override
                 protected void initChannel(SocketChannel ch) throws Exception {
                     // Добавляем обработчики для чтения и записи данных
                    ch.pipeline().addLast(new TcpServerHandler());
                 }
             });

            // Запускаем сервер и ждем подключения клиентов
            ChannelFuture f = b.bind(port).sync();

            LOGGER.log(Level.INFO, "TCP server started on port " + port);

            // Ожидаем завершения работы сервера
            f.channel().closeFuture().sync();
        } finally {
            // Завершаем работу групп потоков
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
