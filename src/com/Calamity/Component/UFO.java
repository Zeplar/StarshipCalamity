package com.Calamity.Component;

import com.Calamity.Color;
import com.Calamity.Color.COLOR;
import com.Calamity.Position;

/**
 * Base class for anything outside of the ship. UFOs will appear on the Sensor Array screen unless they override Render.
 */
public abstract class UFO implements Component {

    private Position position;
    private int size;
    private COLOR color;

    public UFO(Position position, int size, COLOR color)
    {
        this.position = position;
        this.size = size;
        this.color = color;
    }

    public void Render(int millis)
    {

    }
}
