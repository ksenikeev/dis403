package ru.itis.dis403.lab1_09.quaternion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    double phi = Math.PI/180.0*5;
    double i = 1.0;
    double j = 2.0;
    double k = 1.0;
    double cosphi = Math.cos(phi/2.0);
    double c = Math.sin(phi/2.0);

    Quaternion q = new Quaternion(cosphi, i*c, j*c, k*c).unit();
    Vertex[] v = {new Vertex(50.0,50.0,50.0), new Vertex(250.0,50.0,100.0),
            new Vertex(150.0,150.0,200.0),new Vertex(110.0,170.0,90.0)};
    Polygon polygon = new Polygon(v,true);


    public MainFrame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Timer timer = new Timer(100, new ActionListener(){
        	public void actionPerformed(ActionEvent ev) {
        		drawq();
        }});
        timer.start();

        setSize(400, 400);
        setVisible(true);
    }

    public void drawq(){
        this.update(this.getGraphics());
    }
    public void paint(Graphics g) {
          // 	g.clearRect(0,0,400,400);
        polygon.rotate(q);
        polygon.draw(g);
    }
}



