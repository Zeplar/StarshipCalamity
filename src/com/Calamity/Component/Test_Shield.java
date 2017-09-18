package com.Calamity.Component;

import com.Calamity.Color;
import com.Calamity.Hardware.Console_Right;

public class Test_Shield extends Magnetic_Shield {

    private int index, colorIndex;
    private int updates;
    private int color;
    private int switchingTimeMillis = 1000;

    static Color[] colors = {new Color(Color.COLOR.Red), new Color(Color.COLOR.Blue), new Color(Color.COLOR.Green),
            new Color(Color.COLOR.Pink), new Color(Color.COLOR.Cyan), new Color(Color.COLOR.White)};


    public Test_Shield(int switchingTimeMillis)
    {
        this.switchingTimeMillis = switchingTimeMillis;
        Color off = new Color(Color.COLOR.Off);
        for (int i=0; i < 24; i++) Console_Right.instance.ring[i] = off;
    }
    @Override
    public void Update(int millis)
    {
        Console_Right.instance.setText("Time: " + millis);
        updates += millis;

        if (updates > switchingTimeMillis)
        {
            updates -= switchingTimeMillis;
            Console_Right.instance.ring[index] = colors[colorIndex];
            index++;

            if (index == 24) {
                index = 0;
                colorIndex++;
            }
            if (colorIndex == colors.length) colorIndex = 0;
        }

    }

    @Override
    public void Render(int millis) {}
}
