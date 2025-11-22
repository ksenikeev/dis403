package ru.itis.dis403.lab1_08.ball.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class GComponent extends JComponent{

    int x, y, bounds = 64;
    boolean move_up, move_left;
    int speed = 3;

    int bYS = 300, bYC = 300;

    Image image;

    public GComponent() {

        image = new ImageIcon("ball.png").getImage();

        setDoubleBuffered(true);

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                bYS = e.getY();
            }
        });

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (x < 0) {
                    move_left = false;
                }
                if (x > getWidth() - bounds) {
                    move_left = true;
                }

                if (y < 0) {
                    move_up = false;
                }
                if (y > getHeight() - bounds) {
                    move_up = true;
                }

                if (move_left) {
                    x -= speed;
                } else {
                    x += speed;
                }

                if (move_up) {
                    y -= speed;
                } else {
                    y += speed;
                }

                repaint();
            }
        });

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
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
