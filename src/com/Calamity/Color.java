package com.Calamity;

import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * Colors used for the Magnetic Shield and Sensor Array.
 * Contains several preset colors and customized brightness from 0 to 15.
 */
public class Color {

    private byte data;  //The serialized representation of this color
    private java.awt.Color javaColor;   //The GUI representation of this color

    public enum COLOR {Off,Red,Green,Blue,Orange,Pink,Cyan,White};


    public Color(COLOR c, int intensity) {
        if (intensity > 15) {
            throw new IllegalArgumentException("Bad color.");
        }

        data = (byte) ((intensity << 4) + c.ordinal());
        switch (c) {
            case Off:
                javaColor = java.awt.Color.BLACK;
                break;
            case Red:
                javaColor = java.awt.Color.RED;
                break;
            case Green:
                javaColor = java.awt.Color.GREEN;
                break;
            case Blue:
                javaColor = java.awt.Color.BLUE;
                break;
            case Orange:
                javaColor = java.awt.Color.ORANGE;
                break;
            case Pink:
                javaColor = java.awt.Color.PINK;
                break;
            case Cyan:
                javaColor = java.awt.Color.CYAN;
                break;
            case White:
                javaColor = java.awt.Color.WHITE;
                break;
            default:
                throw new IllegalArgumentException("Bad color.");
        }

    }

    public Color(COLOR c)
    {
        this(c, 1);
    }

    public byte get()
    {
        return data;
    }
}
