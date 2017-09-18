package com.Calamity.Component;

import com.Calamity.Color;
import com.Calamity.Position;

public abstract class UFO implements Component {

    private Position position;
    private int size;
    private Color.COLOR color;

    public UFO(Position position, int size, Color.COLOR color)
    {
        this.position = position;
        this.size = size;
        this.color = color;
    }

    public void Render(int millis)
    {

    }
}
