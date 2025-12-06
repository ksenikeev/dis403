package ru.itis.dis403.lab1_10.star;

// UDPClient.java
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UDPStarClient {
    private static final String SERVER_ADDRESS = "itislabs.ru";
    private static final int SERVER_PORT = 50000;
    private static final int TIMEOUT = 5000; // 5 секунд
    private static final int BUFFER_SIZE = 4096;

    public static void main(String[] args) {

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(TIMEOUT);

            InetAddress serverAddress = InetAddress.getByName(SERVER_ADDRESS);
            Scanner scanner = new Scanner(System.in);

            System.out.println("UDP клиент запущен");
            System.out.println("Подключение к серверу: " + SERVER_ADDRESS + ":" + SERVER_PORT);
            System.out.println("Введите 'exit' для выхода");
            System.out.println("Доступные команды: hello, list");
            System.out.println("------------------------------------------");

            while (true) {
                System.out.print("Введите сообщение: ");
                String message = scanner.nextLine();

                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Завершение работы клиента...");
                    break;
                }

                switch (message) {
                    case "hello": {
                        System.out.println("введите имя:");
                        String name = scanner.nextLine();
                        byte[] data = name.getBytes(StandardCharsets.UTF_8);
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        bos.write(0);
                        bos.write(writeInt(data.length));
                        bos.write(data);

                        DatagramPacket sendPacket = new DatagramPacket(
                                bos.toByteArray(),
                                bos.size(),
                                serverAddress,
                                SERVER_PORT
                        );
                        socket.send(sendPacket);
                        break;
                    }
                    case "list": {
                        DatagramPacket sendPacket = new DatagramPacket(
                                new byte[]{1},
                                1,
                                serverAddress,
                                SERVER_PORT
                        );

                        socket.send(sendPacket);

                        byte[] receiveData = new byte[BUFFER_SIZE];
                        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                        try {
                            socket.receive(receivePacket);

                            DataInputStream dis = new DataInputStream(
                                    new ByteArrayInputStream(receivePacket.getData())
                            );

                            int size = dis.readInt();
                            byte[] msg = new byte[size];
                            dis.read(msg);

                            System.out.println("Ответ от сервера: " + new String(msg, StandardCharsets.UTF_8));
                        } catch (SocketTimeoutException e) {
                            System.out.println("Превышено время ожидания ответа от сервера");
                        }
                        break;
                    }
                }

            }

            scanner.close();
        } catch (IOException e) {
            System.err.println("Ошибка клиента: " + e.getMessage());
        }
    }

    private static byte[] writeInt(int value) {
        byte[] result = new byte[4];
        result[0] = (byte) (value >> 24);
        result[1] = (byte) (value >> 16);
        result[2] = (byte) (value >> 8);
        result[3] = (byte) value;
        return result;    }

    private static int readInt(byte[] bytes) {
        return ((bytes[0] & 0xFF) << 24) |
                ((bytes[1] & 0xFF) << 16) |
                ((bytes[2] & 0xFF) << 8) |
                (bytes[3] & 0xFF);
    }

}