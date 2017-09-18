package com.Calamity.Component;

import com.Calamity.Color;
import com.Calamity.Position;
import com.Calamity.Spaceship;

public class Intercepting_Bogey extends UFO {

    private int dx,dy;

    public Intercepting_Bogey(Position position, int speed)
    {
        super(position, 3, Color.COLOR.Red);
        Position towards_ship = position.offset(Spaceship.instance.position);
        dx = towards_ship.getX() * speed;
        dy = towards_ship.getY() * speed;

        double squared = (double)(dx*dx+dy*dy);
        dx = (int)(dx / squared);
        dy = (int)(dy / squared);
    }

    public void Update(int millis)
    {

    }
}
