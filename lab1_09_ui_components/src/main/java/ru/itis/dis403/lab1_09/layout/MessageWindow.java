package ru.itis.dis403.lab1_09.layout;

import javax.swing.*;
import java.awt.*;

public class MessageWindow extends JFrame {

    private String message;

    private JLabel label;

    private JButton closeBtn;

    private JButton buttonLeft;
    private JButton buttonRight;

    private JLabel centerLabel;

    private JFrame frame;

    private JPanel panel;

    public MessageWindow() {
        super("2-е окно");

        frame = this;

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(200, 200);

//        this.setLayout(new FlowLayout());
        this.setLayout(new BorderLayout());

        label = new JLabel();
        this.add(label, BorderLayout.NORTH);

        closeBtn = new JButton("Закрыть");
        closeBtn.addActionListener((e -> {
            frame.setVisible(false);
        }));
        this.add(closeBtn, BorderLayout.SOUTH);

        centerLabel = new JLabel("Центральная");
        buttonLeft = new JButton("лево");
        buttonRight = new JButton("право");

        this.add(buttonLeft, BorderLayout.WEST);
        this.add(buttonRight, BorderLayout.EAST);

        panel = new CentralPane();
        this.add(panel, BorderLayout.CENTER);
    }

    public void setMessage(String message) {
        this.message = message;

        label.setText(message);
    }
}
