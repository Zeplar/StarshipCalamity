package com.Calamity.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ring extends JComponent {


    private int pixels;
    private final int diameter = 10;
    private final double separation = 50;
    private int centerX, centerY;
    private Color[] colors;

    public Ring()
    {
        pixels = 24;

        centerX = diameter * pixels / 2;
        centerY = diameter * pixels / 2;
        colors = new Color[24];
        for (int i=0; i < 24; i++) colors[i] = new java.awt.Color(255*(i%2), 0, 255*((1+i)%2));
    }

    public void setColors(Color[] cs)
    {
        for (int i=0; i < 24; i++) colors[i] = cs[i];
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(250,250);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent((g));
        double angle;
        for (int i=0; i < pixels; i++) {
            g.setColor((colors[i]));
            angle = (2*Math.PI*i)/24;
            Ellipse2D ellipse = new Ellipse2D.Double(centerX + separation*Math.cos(angle), centerY + separation*Math.sin(angle), diameter, diameter);
            ((Graphics2D) g).fill(ellipse);
        }

    }

}
