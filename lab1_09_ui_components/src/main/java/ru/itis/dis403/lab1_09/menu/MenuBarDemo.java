package ru.itis.dis403.lab1_09.menu;

// Демонстрация Меню
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.security.*;
import java.util.HexFormat;

public class MenuBarDemo extends JFrame {

    JPanel pane1;

    public MenuBarDemo() {
        super("Панели");
        // при закрытии окна - выход
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 800);
        //getContentPane().setLayout(new BorderLayout());
        //getContentPane().setLayout(new BorderLayout());


        JRadioButton radio = new JRadioButton("r1");
        JRadioButton radio2 = new JRadioButton("r2");
        radio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(radio.isSelected());
            }
        });

        pane1 = new JPanel();
        getContentPane().add(pane1);

        ButtonGroup gr = new ButtonGroup();
        pane1.add(radio);
        radio.setSelected(true);
        pane1.add(radio2);
        gr.add(radio);
        gr.add(radio2);

        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu file = new JMenu("Файл");
        menuBar.add(file);
        JMenu key = new JMenu("Ключи");
        menuBar.add(key);


        JMenuItem open =new JMenuItem("Открыть");
        file.add(open);
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("open");
            }
        });
        JMenuItem close =new JMenuItem("Закрыть");
        file.add(close);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenu add = new JMenu("Дополнительно");
        file.add(add);
        JMenuItem addPunkt =new JMenuItem("Пунктик");
        add.add(addPunkt);
        addPunkt.addActionListener(System.out::println);

        JMenuItem generate_key =new JMenuItem("Создать");
        key.add(generate_key);
        generate_key.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateKey();
            }
        });


        setVisible(true);
    }


    private void generateKey() {
        KeyPairGenerator rsa = null;
        try (Writer publicKeyWriter = new FileWriter(new File("publik.key"));
             Writer privateKeyWriter = new FileWriter(new File("private.key"))) {
            rsa = KeyPairGenerator.getInstance("RSA");
            rsa.initialize(1024,new SecureRandom());
            KeyPair keyPair = rsa.generateKeyPair();

            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();


            privateKeyWriter.write(HexFormat.of().formatHex(privateKey.getEncoded()));
            publicKeyWriter.write(HexFormat.of().formatHex(publicKey.getEncoded()));

            JOptionPane.showMessageDialog(null, "Ключевая пара успешно создана, \n и сохранена в файлах!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(
                    new Runnable() {
                        public void run() { new MenuBarDemo(); } });
        }
}