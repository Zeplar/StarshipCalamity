package com.Calamity;

public class Color {

    byte data;

    public enum COLOR {Off,Red,Green,Blue,Orange,Pink,Cyan,White};

    public Color(COLOR c, int intensity) {
        if (intensity > 15) {
            intensity = 15;
            System.out.println("Bad color.");
        }
        data = (byte) ((intensity << 4) + c.ordinal());
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
