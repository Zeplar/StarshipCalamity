package com.Calamity.Hardware;

import javax.sql.rowset.serial.SerialException;

public class Console_Center implements Prop {

    boolean lightStates[];
    boolean pushStates[];
    boolean deltaPush[];
    RadioWriter radio;
    private int tries;
    public static Console_Center instance;

    public Console_Center(RadioWriter r)
    {
        if (instance != null)
        {
            System.out.println("Display_Console_Center already exists.");
            return;
        }
        lightStates = new boolean[25];
        pushStates = new boolean[25];
        deltaPush = new boolean[25];
        radio = r;
        instance = this;

    }

    public void Update()
    {
        byte[] data = new byte[32];
        for (int i=0; i < 25; i++) data[i] = (byte)(lightStates[i]?1:0);

        try {
            radio.readWrite(RadioWriter.TARGET.CONSOLE_CENTER, data, data);
        } catch (RadioWriter.RadioException r)
        {
            tries += 1;
            System.out.println("Lost contact with Display_Console_Center: " + tries);
            return;
        }
        catch (SerialException e)
        {
            System.out.println("Lost contact with serial monitor.");
            return;
        }

        for (int i=0; i < 25; i++)
        {
            boolean x = data[i]>0;
            deltaPush[i] = pushStates[i] != x;
            pushStates[i] = x;
        }
    }

    public int getButton(int r, int c)
    {
        return 5*r+c;
    }
}
