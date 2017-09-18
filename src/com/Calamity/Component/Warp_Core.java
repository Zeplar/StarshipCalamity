package com.Calamity.Component;

import com.Calamity.Hardware.Console_Left;
import com.Calamity.Spaceship;

/**
 * The Warp Core draws power and allows player to instantly change ship position, with some restrictions.
 */
public class Warp_Core implements Component {

    static final double maxPowerDrain = 0.5;    //consumes half the ship's power at max power
    static final double minChargeMillis = 10_000.0; //10 seconds to charge at max power
    static final double minCooldownMillis = 30_000.0; //30 seconds to cool down
    double charge;
    double temperature;
    boolean is_warping;
    int countdown_millis;


    /**
     * Deal with charging the warp drive using ship energy
     */
    private void chargeCore(int millis)
    {
        double percentPower = Console_Left.instance.dials[0] / 255.0 * (Console_Left.instance.switches[0]?1:0);
        if (Spaceship.instance.burnEnergy(Math.pow(percentPower,2)*maxPowerDrain))
        {
            charge += percentPower * millis/minChargeMillis;
        }
    }

    public void Render(int millis)
    {
        Console_Left.instance.meters[0] = (byte) (Console_Left.instance.dials[0] * (Console_Left.instance.switches[0]?1:0));
        Console_Left.instance.greenBar = (byte)(charge*10);
        Console_Left.instance.redBar = (byte)(temperature*10);
        Console_Left.instance.yellowLight = (temperature<=0 && charge>= 1);
        Console_Left.instance.redLight = is_warping;

        if (is_warping) {
            Console_Left.instance.yellowLight = (countdown_millis % 400) > 200;
            Console_Left.instance.meters[0] = (byte)255;
        }
    }

    /**
     * Logic for changing ship position.
     */
    private void warp()
    {

    }

    /**
     * Countdown the warp timer and call warp() if it's 0
     * @param millis
     */
    private void warp_countdown(int millis)
    {
        countdown_millis -= millis;
        if (countdown_millis <= 0)
        {
            is_warping = false;
            countdown_millis = 5_000;
            warp();
        }
    }

    public void Update(int millis)
    {
        if (temperature > 0) temperature -= (millis/minCooldownMillis);

        if (charge < 1.0) chargeCore(millis);

        if (charge >= 1 && temperature <= 0 && Console_Left.instance.yellowPushed) is_warping = true;

        if (is_warping && Console_Left.instance.redPushed)
        {
            is_warping = false;
            countdown_millis = 5_000;
        }

        if (is_warping) warp_countdown(millis);

    }
}
