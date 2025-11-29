package ru.itis.dis403.lab1_09.layout;

import javax.swing.*;

/**

 */
public class Test2Frame extends JFrame {

    private JButton button1;
    private JButton button2;
    private JTextField textField;
    private JLabel label1;

    public Test2Frame() {
        super("Пример работы с 2 кнопками");

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setVisible(true);

        // Отключаем автоматическое расположение
        this.setLayout(null);

        textField = new JTextField();
        textField.setBounds(20, 20, 200, 30);
        textField.setText("default value");
        System.out.println(textField.getText());

        button1 = new JButton("Кнопка 1");
        button1.setBounds(20, 50, 200, 30);
        button1.addActionListener(new ButtonActionListener());


        button2 = new JButton("<html><font color=red>Кнопка 2</html>");
        button2.setToolTipText("<html><h3>Это вторая кнопка.<ul>" +
                "Она:<li>Ничего не делает<li>Но ее можно нажать!");
        button2.setBounds(250, 50, 200, 30);
        button2.addActionListener(new ButtonActionListener());

        label1 = new JLabel();
        label1.setBounds(20, 100, 200, 30);
        label1.setText("Метка");

        //label1.setIcon(new ImageIcon("ball.png").getImage());

        this.add(textField);
        this.add(button1);
        this.add(button2);
        this.add(label1);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() { new Test2Frame(); } });
    }

}
