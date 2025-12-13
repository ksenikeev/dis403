package ru.itis.dis403.lab1_09.combobox;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ComboBoxDemo {

    private JFrame mainFrame;
    private JPanel panel;
    private JComboBox<String> comboBox1;
    private JComboBox<DataModel> comboBox2;

    public ComboBoxDemo() {
        init();
    }

    public void init() {
        mainFrame = new JFrame("ComboBox demo");
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setSize(800, 800);
        mainFrame.setLayout(new FlowLayout());

        Object[] listData = {"item1", "item2", "item3"};
        comboBox1 = new JComboBox(listData);

        comboBox2 = new JComboBox<>(initDataModel().toArray(new DataModel[4]));


        JButton button = new JButton("Посмотреть выбор");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "combo 1:" + comboBox1.getSelectedItem() + "\n" +
                        "combo 2: " + ((DataModel)comboBox2.getSelectedItem()).getId(),
                        "Выбранные элементы",JOptionPane.WARNING_MESSAGE);
            }
        });

        mainFrame.add(comboBox1);
        mainFrame.add(comboBox2);
        mainFrame.add(button);

        mainFrame.setVisible(true);
    }

    private List<DataModel> initDataModel() {
        List<DataModel> lst = new ArrayList<>();
        lst.add(new DataModel(1L, "Кабинет 1", new Info(1L, "Кабинет директора")));
        lst.add(new DataModel(2L, "Кабинет 2", new Info(2L, "Кабинет бухгалтера")));
        lst.add(new DataModel(3L, "Кабинет 3", new Info(3L, "Кабинет инженера")));
        lst.add(new DataModel(4L, "Кабинет 4", new Info(4L, "Кабинет заместителя директора")));
        return lst;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() { new ComboBoxDemo(); } });
    }


}

@Data@AllArgsConstructor
class DataModel {
    private Long id;
    private String name;
    private Info info;

    // Конвертор
    public String toString() {
        return info.getData();
    }
}

@Data@AllArgsConstructor
class Info {
    private Long id;
    private String data;
}
