package ru.itis.dis403.lab1_10.udp;

// UDPServer.java
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

public class UDPServer {
    private static final int PORT = 50000;
    private static final int BUFFER_SIZE = 4096;
    private DatagramSocket socket;
    private boolean running;
    private byte[] buffer = new byte[BUFFER_SIZE];

    public UDPServer() {
        try {
            socket = new DatagramSocket(PORT);
            System.out.println("UDP сервер запущен на порту " + PORT);
            running = true;
        } catch (SocketException e) {
            System.err.println("Не удалось запустить сервер: " + e.getMessage());
            System.exit(1);
        }
    }

    public void start() {
        System.out.println("Сервер работает... Нажмите Ctrl+C для остановки");

        while (running) {
            try {
                // Создаем пакет для получения данных от клиента
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);

                // Ожидаем данные
                socket.receive(receivePacket);

                // Получаем информацию о клиенте
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Преобразуем полученные данные в строку
                String receivedData = new String(
                        receivePacket.getData(),
                        0,
                        receivePacket.getLength()
                );

                System.out.println("Получено от " + clientAddress.getHostAddress() +
                        ":" + clientPort + " - " + receivedData);

                // Обрабатываем сообщение
                String response = processMessage(receivedData);

                // Отправляем ответ клиенту
                byte[] responseData = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(
                        responseData,
                        responseData.length,
                        clientAddress,
                        clientPort
                );

                socket.send(sendPacket);
                System.out.println("Ответ отправлен клиенту");

            } catch (IOException e) {
                System.err.println("Ошибка ввода-вывода: " + e.getMessage());
            }
        }

        socket.close();
        System.out.println("Сервер остановлен");
    }

    private String processMessage(String message) {
        // Обработка различных команд
        String trimmedMessage = message.trim().toLowerCase();

        switch (trimmedMessage) {
            case "time":
                return "Текущее время сервера: " + new Date().toString();

            case "date":
                return "Текущая дата: " + new Date().toString();

            case "hello":
                return "Привет от UDP сервера!";

            case "exit":
                running = false;
                return "Сервер завершает работу...";

            case "help":
                return "Доступные команды: time, date, hello, help, exit";

            default:
                return "Эхо: " + message + " (получено " + message.length() + " символов)";
        }
    }

    public static void main(String[] args) {
        UDPServer server = new UDPServer();
        server.start();
    }
}