package ru.itis.dis403.lab1_09.simplebutton;

import javax.swing.*;

/**
 * Работа с кнопкой, полем ввода, меткой
 */
public class TestButton extends JFrame {

    private JButton button;
    private JTextField textField;
    private JLabel label1;

    public TestButton() {
        super("Пример работы с кнопкой");

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 600);
        //this.setTitle("Пример работы с кнопкой");
        this.setVisible(true);

        // Отключаем автоматическое расположение элементов
        this.setLayout(null);

        textField = new JTextField();

        // Вручную указываем позицию и размеры элемента
        textField.setBounds(20, 20, 200, 30);

        button = new JButton("Жми!");
        // Вручную указываем позицию и размеры элемента
        button.setBounds(20, 50, 200, 30);
        // Добавляем обработчик на событие "Нажали кнопку"
        button.addActionListener((event) -> {
            // Текст для метки берем из поля ввода
            label1.setText(textField.getText());
        });

        label1 = new JLabel();
        // Вручную указываем позицию и размеры элемента
        label1.setBounds(20, 100, 200, 30);

        // Добавляем в окно созданные элементы управления
        this.add(textField);
        this.add(button);
        this.add(label1);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() { new TestButton(); } });
    }

}
