package ru.itis.dis403.lab1_09.table;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class TableModelDemo {

    private JFrame mainFrame;
    private JTable table;
    List<Car> cars = new ArrayList<>();

    public TableModelDemo() {
        init();
    }

    public void init() {
        mainFrame = new JFrame("TableDemo demo");
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setSize(800, 800);
        mainFrame.setLayout(new BorderLayout());

        cars.add(new Car(1l,"Renault", "белый", 2019));
        cars.add(new Car(2l,"Kia", "белый", 2017));
        cars.add(new Car(3l,"Opel", "красный", 2020));

        CarTableModel ctm = new CarTableModel(cars);
        table = new JTable(ctm);
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
                cars.add(new Car(4l,"ВАЗ", "синий", 2021));
                ctm.fireTableDataChanged();
            }
        });

        mainFrame.add(addRowButton, BorderLayout.SOUTH);
        mainFrame.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() { new TableModelDemo(); } });
    }


}


class CarTableModel extends AbstractTableModel {

    private List<Car> cars;

    public CarTableModel(List<Car> cars) {
        super();
        this.cars = cars;
    }

    // количество строк
    public int getRowCount() {
        return cars.size();
    }
    // количество столбцов
    public int getColumnCount() {
        return 3;
    }

    // тип данных, хранимых в столбце
    public Class getColumnClass(int column) {
        switch (column) {
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Integer.class;
            default:
                return Object.class;
        }
    }
    // данные в ячейке
    public Object getValueAt(int row, int column) {
        Car car = cars.get(row);
        switch (column) {
            case 0: return car.model;
            case 1: return car.color;
            case 2: return car.yearOfManufacture;
        }
        return "Пусто";
    }

    //ﬁreTableStructureChanged();
}

class Car {
    public Long id;
    public String model;
    public String color;
    public Integer yearOfManufacture;

    public Car(Long id, String model, String color, Integer yearOfManufacture) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.yearOfManufacture = yearOfManufacture;
    }
}