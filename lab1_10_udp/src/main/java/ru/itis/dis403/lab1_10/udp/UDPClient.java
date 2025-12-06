package ru.itis.dis403.lab1_10.udp;

// UDPClient.java
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class UDPClient {
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
            System.out.println("Доступные команды: time, date, hello, help");
            System.out.println("------------------------------------------");

            while (true) {
                System.out.print("Введите сообщение: ");
                String message = scanner.nextLine();

                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Завершение работы клиента...");
                    break;
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

                System.out.println();
            }

            scanner.close();
        } catch (IOException e) {
            System.err.println("Ошибка клиента: " + e.getMessage());
        }
    }
}