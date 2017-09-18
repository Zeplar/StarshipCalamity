package com.Calamity.GUI;

import javafx.scene.layout.Background;

import javax.swing.*;
import java.awt.*;

public class Display_Console {

    private Background Background;
    public Display_Console(Display_Console_Center c, Display_Console_Right r) {
        JFrame frame = new JFrame("Display_Console");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JSplitPane splitPlane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, c.get(), r.get());
        splitPlane.setDividerSize(30);
        splitPlane.setDividerLocation(0.5);
        frame.add(splitPlane);
        frame.pack();
        frame.setVisible(true);
    }
}
