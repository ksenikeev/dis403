package ru.itis.dis403.lab1_09.table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class TableDefaultModelDemo {

    private JFrame mainFrame;
    private JTable table;

    private DefaultTableModel dtm;

    public TableDefaultModelDemo() {
        init();
    }

    public void init() {
        mainFrame = new JFrame("TableDemo demo");
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setSize(800, 800);
        mainFrame.setLayout(new BorderLayout());

        dtm = new DefaultTableModel();
        dtm.setColumnIdentifiers(new String[]{"col 1","col 2","col 3"});
        dtm.addRow(new String[]{"item11", "item12", "item13"});
        dtm.addRow(new String[]{"item21", "item22", "item23"});
        dtm.addRow(new String[]{"item31", "item32", "item33"});

        table = new JTable(dtm);
        table.setRowHeight(40);
        table.setIntercellSpacing(new Dimension(10, 10));
        table.setGridColor(Color.green);
        table.setShowVerticalLines(false);

        table.setForeground(Color.red);
        table.setSelectionForeground(Color.yellow);
        table.setSelectionBackground(Color.blue);

        mainFrame.add(new JScrollPane(table), BorderLayout.NORTH);

        JButton addRowButton = new JButton("Добавить");
        addRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dtm.addRow(new String[]{"item41", "item42", "item43"});
            }
        });

        mainFrame.add(addRowButton, BorderLayout.SOUTH);
        mainFrame.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() { new TableDefaultModelDemo(); } });
    }


}

