package com.Calamity;
import com.Calamity.Component.Warp_Core;
import com.Calamity.Hardware.Console_Center;
import com.Calamity.Hardware.Console_Left;
import com.Calamity.Hardware.Console_Right;
import com.Calamity.Hardware.RadioWriter;
import com.Calamity.Component.Component;

import java.util.List;

public class Spaceship {
    private double fuel;
    private double energy;
    public Position position;
    private int dX, dY;
    private double angleDegree;
    private double dAngle;

    private RadioWriter radio;

    private int deltaMicros;

    private List<Component> components;

    public static Spaceship instance;

    enum Rocket {
        leftRear, rightRear, leftTurn, rightTurn, hyper1, hyper2
    };

    public Spaceship()
    {
        if (instance != null)
        {
            System.out.println("Spaceship already exists.");
            return;
        }
        fuel = 1;   //Amount of currently-available fuel.
        energy = 1; //Amount of currently-available energy.
        position = new Position(0,0,0);
        angleDegree = 0;
        instance = this;
        radio = new RadioWriter();

        new Console_Center(radio);
        new Console_Left(radio);
        new Console_Right(radio);

        components.add(new Warp_Core());
    }

    public void Update(int millis)
    {
        if (energy < 1) energy += 1;
        for (Component c : components) {
            c.Update(millis);
        }
        for (Component c : components) {
            c.Render(millis);
        }

        Console_Left.instance.Update();
        Console_Center.instance.Update();
        Console_Right.instance.Update();

    }

    /**
     *
     * @param e The amount of energy to burn
     * @return  Whether the burn was successful (the ship had enough energy)
     */
    public boolean burnEnergy(double e)
    {
        if (energy > e)
        {
            energy -= e;
            return true;
        }
        return false;
    }

    public boolean burnFuel(double f)
    {
        if (fuel > f)
        {
            fuel -= f;
            return true;
        }
        return false;
    }



}