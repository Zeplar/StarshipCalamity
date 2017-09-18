package com.Calamity.Hardware;

import javax.sql.rowset.serial.SerialException;

/**
 * Pipes allow players to control the use of elements, and display the amount of elements remaining.
 */
public class Pipes implements Prop {

    private static Pipes instance;
    private RadioWriter radio;

    public boolean H_valve, N_valve, O_valve;
    public byte H_meter, N_meter, O_meter;
    private byte[] data;
    private int tries;

    public Pipes(RadioWriter radio)
    {
        if (instance != null)
        {
            System.out.println("Pipes already exist.");
            return;
        }
        this.radio = radio;
        data = new byte[32];
        instance = this;
    }

    @Override
    public void Update() {
        data[0] = H_meter;
        data[1] = N_meter;
        data[2] = O_meter;

        try {
            radio.readWrite(RadioWriter.TARGET.PIPES, data, data);
        } catch (RadioWriter.RadioException r)
        {
            tries += 1;
            System.out.println("Lost contact with Console_Right: " + tries);
            return;
        } catch (SerialException e)
        {
            System.out.println("Lost contact with serial monitor.");
            return;
        }
        H_valve = data[0] > 0;
        N_valve = data[1] > 0;
        O_valve = data[2] > 0;

    }
}
