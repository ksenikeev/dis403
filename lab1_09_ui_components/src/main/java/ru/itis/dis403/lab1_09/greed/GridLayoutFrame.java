package ru.itis.dis403.lab1_09.greed;

import javax.swing.*;
import java.awt.*;

/**
 * Используем менеджер расположения GridLayout - элементы располагаются в ячейках таблицы
 *
 * GridLayout(КоличествоСтрок, КоличествоСтолбцов, РасстояниеМеждуЯчейкамиПоX, РасстояниеМеждуЯчейкамиПоY);
 * КоличествоСтрок, КоличествоСтолбцов - не нулевым должно быть только одно из двух
 */
public class GridLayoutFrame extends JFrame {

    private JButton[] buttons;

    public GridLayoutFrame() {
        super("Пример работы с 2 кнопками");

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000, 1000);

        this.setLayout(new GridLayout(0, 2, 50, 20));

        buttons = new JButton[15];
        for (int i = 0; i < 15; ++i) {
            buttons[i] = new JButton("Кнопка " + i);
            this.add(buttons[i]);
        }

        //this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() { new GridLayoutFrame(); } });
    }

}
