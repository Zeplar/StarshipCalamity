package com.Calamity.Component;

import com.Calamity.Hardware.Console_Right;
import com.Calamity.Spaceship;
import com.Calamity.Color;

public class Magnetic_Shield implements Component {

    enum DIRECTION {N, NE, E, SE, S, SW, W, NW};
    private static int directions[] = {0, 3, 6, 9, 12, 15, 18, 21};

    private double[] hp;        //Integrity of the shield at each location
    static final double maxPowerDrain = 0.5;    //Ship energy drained per tick at max power
    static final double minChargeMillis = 10_000;   //Min time to charge shield to full at max power
    static final Color[] colors = {new Color(Color.COLOR.Green), new Color(Color.COLOR.Orange), new Color(Color.COLOR.Red)};

    /**
     * Returns an array of the 3 shield pixels around the given direction
     * @param d
     * @return
     */
    private int[] getDirection(DIRECTION d)
    {
        int i = directions[d.ordinal()];
        int[] ret = {i-1, i, i+1};
        if (ret[0] < 0) ret[0] = 23;
        return ret;
    }

    public Magnetic_Shield()
    {
        hp = new double[24];
    }

    /**
     * Damages the shield at a location. Returns whether the shield was destroyed.
     * @param direction
     * @param damage
     * @return
     */
    public boolean takeDamage(DIRECTION direction, double damage)
    {
        boolean destroyed = false;
        for (int i : getDirection(direction))
        {
            hp[i] -= damage;
            if (hp[i] < 0) destroyed = true;
        }
        return destroyed;
    }

    public void Update(int millis)
    {
        double powerLevel = Console_Right.instance.sliders[0] / 255 * (double)(Console_Right.instance.switches[0]?1:0);

        if (Spaceship.instance.burnEnergy(powerLevel * maxPowerDrain ))
        {
            for (int i=0; i < 24; i++)
            {
                if (hp[i] < 1) hp[i] += (powerLevel * millis/minChargeMillis);
            }
        }
    }

    public void Render(int millis)
    {
        Color c;
        for (int i=0; i < 24; i++)
        {
            if (hp[i] < 0.2) c = colors[2];
            else if (hp[i] < 0.7) c = colors[1];
            else c = colors[0];

            Console_Right.instance.ring[i] = c;
        }
    }
}