package ru.itis.dis403.lab1_09.list;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ListDemo {

    private JFrame mainFrame;
    private JPanel panel;
    private JList itemList;

    public ListDemo() {
        init();
    }

    public void init() {
        mainFrame = new JFrame("List demo");
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setSize(800, 800);

        String[] listData = {"item1", "item2", "item3"};
        itemList = new JList(listData);
        //itemList.setListData(listData);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.addListSelectionListener(new ListSelection());

        mainFrame.add(new JScrollPane(itemList));

        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() { new ListDemo(); } });
    }

}

class ListSelection implements ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("selected item:" + e.getFirstIndex() + ", "
                + ((JList)e.getSource()).getSelectedValue()
        + ((JList)e.getSource()).getSelectedIndex());
    }
}