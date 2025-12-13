package ru.itis.dis403.lab1_10.starp2p.star;

// UDPClient.java

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UDPStarP2PClient extends ClientData {
    private static final String SERVER_ADDRESS = "itislabs.ru";
    private static final int SERVER_PORT = 50000;
    private static final int BUFFER_SIZE = 4096;

    public static void main(String[] args) {

        try (DatagramSocket socket = new DatagramSocket()) {
            //socket.setSoTimeout(TIMEOUT);

            InetAddress serverAddress = InetAddress.getByName(SERVER_ADDRESS);
            Scanner scanner = new Scanner(System.in);

            System.out.println("UDP клиент запущен");
            System.out.println("Подключение к серверу: " + SERVER_ADDRESS + ":" + SERVER_PORT);
            System.out.println("Введите 'exit' для выхода");
            System.out.println("Доступные команды: hello, list, message");
            System.out.println("------------------------------------------");

            // параллельный процесс прослушивания ответов на сокет
            new Thread(() -> {
                byte[] receiveData = new byte[BUFFER_SIZE];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                try {
                    socket.receive(receivePacket);

                    DataInputStream dis = new DataInputStream(
                            new ByteArrayInputStream(receivePacket.getData())
                    );

                    byte typeMessage = dis.readByte();

                    switch (typeMessage) {
                        case 1: {
                            int size = dis.readInt();
                            byte[] msg = new byte[size];
                            dis.read(msg);
                            System.out.println(new String(msg));
                            break;
                        }
                        case 2: {
                            int gamerId = dis.readInt();
                            int gamerPort = dis.readInt();
                            int size = dis.readInt();
                            byte[] adr = new byte[size];
                            dis.read(adr);
                            InetAddress gamerAddress = InetAddress.getByName(new String(adr));

                            break;
                        }
                        case 3: {

                            break;
                        }
                    }
                    int clientId = 0;
                    if (typeMessage == 2) {
                        clientId = dis.readInt();
                    }
                    int size = dis.readInt();
                    byte[] msg = new byte[size];
                    dis.read(msg);

                    System.out.println("Ответ от сервера: " + typeMessage + ": "
                            + clientId + ":"
                            + new String(msg, StandardCharsets.UTF_8));

                }  catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }).start();

            // опрос ввода с консоли
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

                        break;
                    }
                    case "message": {
                        System.out.println("введите id получателя:");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("введите сообщение получателю:" + id);
                        String msg = scanner.nextLine();
                        System.out.println("Ввели " + msg);

                        byte[] data = msg.getBytes(StandardCharsets.UTF_8);
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        bos.write(2);
                        bos.write(writeInt(id));
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