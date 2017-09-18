package com.Calamity.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Display_Console_Right {
    private JPanel Background;
    private Ring Shield;
    private JSlider slider1;
    private JSlider slider2;
    private JRadioButton engine0;
    private JRadioButton engine5;
    private JRadioButton engine4;
    private JRadioButton engine3;
    private JRadioButton engine2;
    private JRadioButton engine1;
    private JRadioButton switch1;
    private JRadioButton switch2;
    private JTextArea LCD;
    private JTextField clock1;
    private JTextField clock2;


    public void setRing(java.awt.Color[] cs)
    {
        Shield.setColors(cs);
    }

    public byte[] getSliders()
    {
        return new byte[] {(byte) slider1.getValue(), (byte) slider2.getValue()};
    }

    public void setSliders(int[] sliders)
    {
        slider1.setValue(sliders[0]);
        slider2.setValue(sliders[1]);
    }

    public boolean[] getEngines()
    {
        boolean[] engines = new boolean[] {engine0.isSelected(),engine1.isSelected(),engine2.isSelected(),
        engine3.isSelected(),engine4.isSelected(),engine5.isSelected()};
        return engines;
    }
    public void setEngines(boolean[] engines)
    {
        engine0.setSelected((engines[0]));
        engine1.setSelected((engines[1]));
        engine2.setSelected((engines[2]));
        engine3.setSelected((engines[3]));
        engine4.setSelected((engines[4]));
        engine5.setSelected((engines[5]));
    }

    private void createUIComponents() {
        Shield = new Ring();
    }

    public void run() {
        JFrame frame = new JFrame("Display_Console_Right");
        frame.setContentPane(Background);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel get()
    {
        return Background;
    }
}
