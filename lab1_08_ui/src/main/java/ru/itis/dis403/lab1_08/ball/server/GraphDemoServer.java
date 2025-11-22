package ru.itis.dis403.lab1_08.ball.server;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GraphDemoServer extends JFrame {

    public GraphDemoServer() throws IOException {
        super("game");

        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //createBufferStrategy(2);
        setLayout(new BorderLayout());

        ServerSocket socket = new ServerSocket(50000);
        Socket clientSocket = socket.accept();
        add(new GComponent(clientSocket));
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        try {
                            new GraphDemoServer();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

}
