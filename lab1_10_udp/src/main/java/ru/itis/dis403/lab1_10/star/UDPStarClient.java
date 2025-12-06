package ru.itis.dis403.lab1_10.star;

// UDPClient.java
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UDPStarClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 50000;
    private static final int TIMEOUT = 5000; // 5 секунд
    private static final int BUFFER_SIZE = 4096;

    public static void main(String[] args) {
/*
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
                            String response = new String(
                                    receivePacket.getData(),
                                    0,
                                    receivePacket.getLength()
                            );
                            System.out.println("Ответ от сервера: " + response);
                        } catch (SocketTimeoutException e) {
                            System.out.println("Превышено время ожидания ответа от сервера");
                        }
                    }

                }

                // Отправляем сообщение серверу
                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(
                        sendData,
                        sendData.length,
                        serverAddress,
                        SERVER_PORT
                );

                socket.send(sendPacket);
                System.out.println("Сообщение отправлено серверу");

                // Получаем ответ от сервера

                System.out.println();
            }

            scanner.close();
        } catch (IOException e) {
            System.err.println("Ошибка клиента: " + e.getMessage());
        }
*/
        int i = 10223234;
        System.out.println(readInt(writeInt(i)));
    }

    private static byte[] writeInt(int i) {
        byte[] result = {
                (byte)((i >>> 24) & 0xFF),
                (byte)((i >>> 16) & 0xFF),
                (byte)((i >>> 8) & 0xFF),
                (byte)(i & 0xFF)
        };
        return result;
    }

    private static int readInt(byte[] data) {
        return  (data[0] << 24) | (data[1] << 16) | (data[2] << 8) | ((int)data[3]);

    }

}