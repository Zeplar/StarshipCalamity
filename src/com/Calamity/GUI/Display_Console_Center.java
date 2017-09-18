package com.Calamity.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class Display_Console_Center {
    private JCheckBox button1;
    private JCheckBox button2;
    private JCheckBox button3;
    private JCheckBox button4;
    private JCheckBox button5;
    private JCheckBox button6;
    private JCheckBox button7;
    private JCheckBox button8;
    private JCheckBox button9;
    private JCheckBox button10;
    private JCheckBox button11;
    private JCheckBox button12;
    private JCheckBox button13;
    private JCheckBox button14;
    private JCheckBox button15;
    private JCheckBox button16;
    private JCheckBox button17;
    private JCheckBox button18;
    private JCheckBox button19;
    private JCheckBox button20;
    private JCheckBox button21;
    private JCheckBox button22;
    private JCheckBox button23;
    private JCheckBox button24;
    private JCheckBox button25;

    private JPanel Background;

    private JCheckBox[] Buttons;

    public Display_Console_Center()
    {
        Buttons = new JCheckBox[]
        {
        button1, button2, button3,button4,button5,
        button6,button7,button8,button9,button10,
        button11,button12,button13,button14,button15,
        button16,button17,button18,button19,button20,
        button21,button22,button23,button24,button25};
    }

    public void setButton(int b, boolean pressed)
    {
        Buttons[b].setSelected(pressed);
    }

    public boolean getButton(int b)
    {
        return Buttons[b].isSelected();
    }
    public boolean[] getButtons()
    {
        boolean[] bs = new boolean[25];
        for (int i=0; i < 25; i++) bs[i] = getButton(i);
        return bs;
    }
    public void setButtons(boolean[] b)
    {
        for (int i=0; i < 25; i++) setButton(i,b[i]);
    }

    public void run() {
        JFrame frame = new JFrame("Display_Console_Center");
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
