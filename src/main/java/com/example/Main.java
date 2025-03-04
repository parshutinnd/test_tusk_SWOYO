package com.example;

import java.util.Scanner;

import com.example.client.server.Client;
import com.example.server.tcpserver.Server;

public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in); // Создаем объект Scanner для чтения с консоли
        System.out.println("Введите режим работы: s - режим сервера с - режим клиента"); // Выводим приглашение к вводу
        String regime = scanner.nextLine();

        final AServer s;

        if (regime.equals("s")){
            s = new Server(8080);
        } else {
            s = new Client("localhost", 8080);
        }

        Thread serverThread = new Thread(() -> {
            try {
                s.init();
            } catch (Exception ex) {
            }
        });

        serverThread.start();

        try {
            serverThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}