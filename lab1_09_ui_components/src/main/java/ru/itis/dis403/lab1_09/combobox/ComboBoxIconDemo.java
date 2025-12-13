package ru.itis.dis403.lab1_09.combobox;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ComboBoxIconDemo {

    private JFrame mainFrame;
    private JPanel panel;
    private JComboBox<String> comboBox1;
    private JComboBox<IcoData> comboBox2;

    public ComboBoxIconDemo() {
        init();
    }

    public void init() {
        mainFrame = new JFrame("ComboBox demo");
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setSize(800, 800);
        mainFrame.setLayout(new FlowLayout());

        Object[] listData = {"item1", "item2", "item3"};
        comboBox1 = new JComboBox(listData);

        //comboBox2 = new JComboBox<>(initDataModel().toArray(new DataModel[4]));


        JButton button = new JButton("Посмотреть выбор");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "combo 1:" + comboBox1.getSelectedItem() + "\n" +
                                "combo 2: " + ((IcoData) comboBox2.getSelectedItem()).getText(),
                        "Выбранные элементы", JOptionPane.WARNING_MESSAGE,
                        ((IcoData) comboBox2.getSelectedItem()).getIcon());
            }
        });

        IcoData[] lstIcoData = {new IcoData(new ImageIcon("ico1.png"), "Директор"),
                new IcoData(new ImageIcon("ico2.png"), "Инженер"),
                new IcoData(new ImageIcon("ico3.png"), "Бухгалтер"),
                new IcoData(new ImageIcon("ico4.png"), "Заместитель директора")};

        comboBox2 = new JComboBox<IcoData>(lstIcoData);

        comboBox2.setRenderer(new ImageListCellRenderer());

        mainFrame.add(comboBox1);
        mainFrame.add(comboBox2);
        mainFrame.add(button);

        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        new ComboBoxIconDemo();
                    }
                });
    }

}

@Data@AllArgsConstructor
class IcoData {
    private Icon icon;
    private String text;

    @Override
    public String toString() {
        return text;
    }
}

class ImageListCellRenderer extends DefaultListCellRenderer {
    // метод, возвращающий для элемента рисующий компонент
    public Component getListCellRendererComponent(
            JList list, Object data, int idx, boolean isSelected,
            boolean hasFocus) {
// проверяем, нужного ли элемент типа
        if (data instanceof IcoData) {
            IcoData lie = (IcoData) data;
            Icon icon = lie.getIcon();
            String text = lie.getText();
            JLabel label = (JLabel)
                    super.getListCellRendererComponent(
                            list, text, idx, isSelected, hasFocus);
            label.setIcon(icon);
            return label;
        } else
            return super.getListCellRendererComponent(
                    list, data, idx, isSelected, hasFocus);
    }

}