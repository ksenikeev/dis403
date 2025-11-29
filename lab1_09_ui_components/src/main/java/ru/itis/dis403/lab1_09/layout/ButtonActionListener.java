package ru.itis.dis403.lab1_09.layout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс - обработчик сообщения от органа управления
 */
public class ButtonActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JButton) {
            JButton btn = (JButton) e.getSource();

            if (btn.getText().equals("Кнопка 1")) {
                System.out.println("pressed btn 1");

                MessageWindow messageWindow = new MessageWindow();
                messageWindow.setMessage("Hello!");
                messageWindow.setVisible(true);

            } else if (btn.getText().equals("Кнопка 2")) {
                System.out.println("pressed btn 2");
            }

        } else {
            System.out.println("иной орган управления");
        }
    }
}
