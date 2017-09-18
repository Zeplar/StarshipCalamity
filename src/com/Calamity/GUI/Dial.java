package com.Calamity.GUI;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;
import javax.swing.*;

public class Dial extends JComponent {
    int minValue, value, maxValue, radius;

    public Dial( ) { this(0, 100, 0); }

    public Dial(int minValue, int maxValue, int value) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = value;
        setForeground(Color.lightGray);

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        int tick = 10;
        radius = getSize( ).width / 2 - tick;
        g2.setPaint(getForeground().darker( ));
        g2.drawLine(radius * 2 + tick / 2, radius,
                radius * 2 + tick, radius);
        g2.setStroke(new BasicStroke(2));
        draw3DCircle(g2, 0, 0, radius, true);
        int knobRadius = radius / 7;
        double th = value * (2 * Math.PI) / (maxValue - minValue);
        int x = (int)(Math.cos(th) * (radius - knobRadius * 3)),
                y = (int)(Math.sin(th) * (radius - knobRadius * 3));
        g2.setStroke(new BasicStroke(1));
        draw3DCircle(g2, x + radius - knobRadius,
                y + radius - knobRadius, knobRadius, false );
    }

    private void draw3DCircle( Graphics g, int x, int y,
                               int radius, boolean raised) {
        Color foreground = getForeground( );
        Color light = foreground.brighter( );
        Color dark = foreground.darker( );
        g.setColor(foreground);
        g.fillOval(x, y, radius * 2, radius * 2);
        g.setColor(raised ? light : dark);
        g.drawArc(x, y, radius * 2, radius * 2, 45, 180);
        g.setColor(raised ? dark : light);
        g.drawArc(x, y, radius * 2, radius * 2, 225, 180);
    }

    public Dimension getPreferredSize( ) {
        return new Dimension(100, 100);
    }

    public void setValue(int value) {
        firePropertyChange( "value", this.value, value );
        this.value = value;
        repaint( );
    }
    public int getValue( )  { return value; }
    public void setMinimum(int minValue)  { this.minValue = minValue; }
    public int getMinimum( )  { return minValue; }
    public void setMaximum(int maxValue)  { this.maxValue = maxValue; }
    public int getMaximum( )  { return maxValue; }
}