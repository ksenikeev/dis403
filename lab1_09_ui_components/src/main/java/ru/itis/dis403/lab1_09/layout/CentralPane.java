package ru.itis.dis403.lab1_09.layout;

import javax.swing.*;
import java.awt.*;

public class CentralPane extends JPanel {

    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JLabel label1;
    private JLabel label2;

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;

    public CentralPane() {
        super();

        this.setLayout(new FlowLayout());

        textField1 = new JTextField();
        textField1.setPreferredSize(new Dimension(100, 30));
        textField2 = new JTextField(20);
        textField3 = new JTextField(20);
        textField4 = new JTextField(20);
        textField5 = new JTextField(20);
        textField6 = new JTextField(20);

        button1 = new JButton("Кнопка 1");
        button2 = new JButton("Кнопка 2");
        button3 = new JButton("Кнопка 3");
        button4 = new JButton("Кнопка 4");

        label1 = new JLabel("Метка 1");
        label2 = new JLabel("Метка 2");

        this.add(label1);
        this.add(textField1);

        this.add(label2);
        this.add(textField2);

        this.add(textField3);
        this.add(textField4);
        this.add(textField5);
        this.add(textField6);

        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);
    }
}
