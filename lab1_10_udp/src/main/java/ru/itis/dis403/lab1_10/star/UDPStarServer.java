package ru.itis.dis403.lab1_10.star;

// UDPServer.java
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UDPStarServer {
    private static final int PORT = 50000;
    private static final int BUFFER_SIZE = 4096;
    private DatagramSocket socket;

    private List<ClientData> clients;

    private boolean running;
    private byte[] buffer = new byte[BUFFER_SIZE];

    public UDPStarServer() {
        clients = new ArrayList<>();
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

                // Обрабатываем сообщение
                processMessage(receivePacket.getData(), socket);


            } catch (IOException e) {
                System.err.println("Ошибка ввода-вывода: " + e.getMessage());
            }
        }

        socket.close();
        System.out.println("Сервер остановлен");
    }

    private void processMessage(byte[] data, DatagramSocket socket) throws IOException {

        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
        byte msgType = dis.readByte();

        switch (msgType) {
            case 0 :
                int length = dis.readInt();
                byte[] buf = new byte[length];
                dis.read(buf, 0, length);
                String name = new String(buf, StandardCharsets.UTF_8);

        }

        // Отправляем ответ клиенту
        byte[] responseData = response.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(
                responseData,
                responseData.length,
                clientAddress,
                clientPort
        );

        socket.send(sendPacket);
    }

    public static void main(String[] args) {
        UDPStarServer server = new UDPStarServer();
        server.start();
    }
}