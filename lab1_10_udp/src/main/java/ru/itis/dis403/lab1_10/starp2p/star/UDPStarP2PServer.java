package ru.itis.dis403.lab1_10.starp2p.star;

// UDPServer.java
/**
    Протокол:
    сообщение строится начиная с байта, описывающего тип сообщения
    0: сообщение hello (команда (0) | int - длина сообщения | сообщение (имя игрока) [направление Клиент -> Сервер]
    1: сообщение list - запрос списка игроков (без дополнительных параметров) [направление Клиент -> Сервер]
    1: сообщение list - список игроков (команда (1) | int - длина сообщения | сообщение JSON массив с игроками) [направление Сервер -> Клиент]
    2: выбор игрока (команда (2) | int - id игрока) [направление Клиент -> Сервер]
    2: передача информации о другом игроке [направление Сервер -> Клиент]
        (команда (2) | int - id игрока | int - port 2-го игрока | int - длина адреса 2-го игрока | адрес 2-го игрока строкой)
        ответ рассылается обоим игрокам
    3: передача сообщения другому игроку [направление Клиент -> Клиент]
        (команда (3) | int - id игрока | int - длина сообщения | сообщение)
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UDPStarP2PServer {
    private static final int PORT = 50000;
    private static final int BUFFER_SIZE = 4096;
    private DatagramSocket socket;

    private List<ClientData> clients;

    private boolean running;
    private volatile AtomicInteger nextClientId = new AtomicInteger(1);// использовать только для инкремента
    private byte[] buffer = new byte[BUFFER_SIZE];

    public UDPStarP2PServer() {
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
        // читаем команду
        byte msgType = dis.readByte();

        switch (msgType) {
            case 0: { // сообщение hello (команда (0) | int - длина сообщения | сообщение (имя игрока)
                int length = dis.readInt();
                byte[] buf = new byte[length];
                dis.read(buf, 0, length);
                String name = new String(buf, StandardCharsets.UTF_8);
                clients.add(new ClientData(nextClientId.getAndIncrement(), name, 
                        receivePacket.getAddress(), receivePacket.getPort()));
                System.out.println("Добавили клиента " + name + " : " + receivePacket.getAddress().getHostAddress() + ":" + receivePacket.getPort());
                break;
            }
            case 1: { // сообщение list (запрос без параметров)
                ObjectMapper mapper = new ObjectMapper();
                String message = mapper.writeValueAsString(clients);
                byte[] dataMessage = message.getBytes(StandardCharsets.UTF_8);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bos.write((byte) 1); // отвечаем на команду "1"
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
            case 2: {
                /*
                     команда (2) | int - id игрока |
                     отвечаем (команда (2) | int - id игрока | int - port 2-го игрока | int - длина адреса 2-го игрока | адрес 2-го игрока строкой)
                 */
                int id = dis.readInt();

                ClientData otherClient = clients.stream()
                        .filter(c -> c.getId() == id).findFirst().orElse(null);
                System.out.println("нашли другого клиента " + otherClient);
                ClientData thisClient = clients.stream()
                        .filter(c -> c.getClientAddress().equals(receivePacket.getAddress()) && c.getClientPort()==receivePacket.getPort())
                        .findFirst().orElse(null);
                System.out.println("нашли самого клиента " + thisClient);

                // отвечаем обоим клиентам
                if (otherClient != null && thisClient != null) {
                    System.out.println("отвечаем другому клиенту");
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bos.write((byte) 2); // отвечаем на команду "2"
                    bos.write(writeInt(thisClient.getId())); //
                    bos.write(writeInt(thisClient.getClientPort()));
                    byte[] thisAddress = thisClient.getClientAddress().getHostAddress().getBytes();
                    bos.write(writeInt(thisAddress.length));
                    bos.write(thisAddress);

                    DatagramPacket sendPacket = new DatagramPacket(
                            bos.toByteArray(),
                            bos.size(),
                            otherClient.getClientAddress(),
                            otherClient.getClientPort());
                    socket.send(sendPacket);

                    System.out.println("отвечаем самому клиенту");
                    bos = new ByteArrayOutputStream();
                    bos.write((byte) 2); // отвечаем на команду "2"
                    bos.write(writeInt(otherClient.getId())); //
                    bos.write(writeInt(otherClient.getClientPort()));
                    byte[] otherAddress = otherClient.getClientAddress().getHostAddress().getBytes();
                    bos.write(writeInt(otherAddress.length));
                    bos.write(otherAddress);

                    sendPacket = new DatagramPacket(
                            bos.toByteArray(),
                            bos.size(),
                            thisClient.getClientAddress(),
                            thisClient.getClientPort());
                    socket.send(sendPacket);
                } else {
                    //здесь надо ответить, что не нашли клиента-получателя
                }
                break;
            }
        }
    }

    private byte[] writeInt(int value) {
        byte[] result = new byte[4];
        result[0] = (byte) (value >> 24);
        result[1] = (byte) (value >> 16);
        result[2] = (byte) (value >> 8);
        result[3] = (byte) value;
        return result;
    }

    public static void main(String[] args) {
        UDPStarP2PServer server = new UDPStarP2PServer();
        server.start();
    }
}