package ru.itis.dis403.lab1_08.ball.client;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class GComponentClient extends JComponent {

    int x, y, bounds = 64;
    boolean move_up, move_left;
    int speed = 3;

    int bYS = 300, bYC = 300;

    Image image;

    Socket socket;

    public GComponentClient() throws IOException {

        socket = new Socket("127.0.0.1", 50000);

        image = new ImageIcon("ball.png").getImage();

        setDoubleBuffered(true);

        new Thread(() -> {
            while (true) {
                DataInputStream dis = null;
                try {
                    dis = new DataInputStream(socket.getInputStream());
                    bYS = dis.readInt();
                    x = dis.readInt();
                    y = dis.readInt();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                repaint();
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println(bYS);
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(image,x,y,null);

        g2d.fillRect(10, bYS, 10, 200);
        g2d.fillRect(this.getWidth() - 20, bYC, 10, 200);

        g2d.dispose();

        Toolkit.getDefaultToolkit().sync();
    }
}
