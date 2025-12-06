package ru.itis.dis403.lab1_10.star;

// UDPServer.java
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UDPStarServer {
    private static final int PORT = 50000;
    private static final int BUFFER_SIZE = 4096;
    private DatagramSocket socket;

    private List<ClientData> clients;

    private boolean running;
    private volatile int nextClientId = 1;
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
                processMessage(receivePacket);


            } catch (IOException e) {
                System.err.println("Ошибка ввода-вывода: " + e.getMessage());
            }
        }

        socket.close();
        System.out.println("Сервер остановлен");
    }

    private void processMessage(DatagramPacket receivePacket) throws IOException {

        byte[] data = receivePacket.getData();
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
        byte msgType = dis.readByte();

        switch (msgType) {
            case 0:
                int length = dis.readInt();
                byte[] buf = new byte[length];
                dis.read(buf, 0, length);
                String name = new String(buf, StandardCharsets.UTF_8);
                clients.add(new ClientData(nextClientId++, name,
                        receivePacket.getAddress(), receivePacket.getPort()));
                System.out.println("Добавили клиента " + name);
                break;
            case 1:
                ObjectMapper mapper = new ObjectMapper();
                String message = mapper.writeValueAsString(clients);
                byte[] dataMessage = message.getBytes(StandardCharsets.UTF_8);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bos.write(writeInt(dataMessage.length));
                bos.write(dataMessage);

                DatagramPacket sendPacket = new DatagramPacket(
                        bos.toByteArray(),
                        bos.size(),
                        receivePacket.getAddress(),
                        receivePacket.getPort());
                socket.send(sendPacket);
                break;
        }
    }

    private byte[] writeInt(int i) {
        byte[] result = {
                (byte)((i >>> 24) & 0xFF),
                (byte)((i >>> 16) & 0xFF),
                (byte)((i >>> 8) & 0xFF),
                (byte)(i & 0xFF)
        };
        return result;
    }

    public static void main(String[] args) {
        UDPStarServer server = new UDPStarServer();
        server.start();
    }
}