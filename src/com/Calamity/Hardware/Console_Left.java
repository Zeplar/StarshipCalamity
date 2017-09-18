package com.Calamity.Hardware;

import javax.sql.rowset.serial.SerialException;

public class Console_Left implements Prop {

    public byte greenBar, redBar;
    public boolean[] switches;
    public boolean yellowLight, yellowPushed, redLight, redPushed;
    public byte[] meters;
    public byte[] dials;
    public boolean key;
    private RadioWriter radio;
    private int tries;

    public static Console_Left instance;


    public Console_Left(RadioWriter r)
    {
        if (instance != null)
        {
            System.out.println("Console_Left already exists.");
            return;
        }
        radio = r;
        switches = new boolean[3];
        meters = new byte[3];
        dials = new byte[4];
        instance = this;
    }

    public void Update()
    {
        //pack data
        byte[] to_send = {redBar, greenBar, meters[0],meters[1],meters[2], (byte)(yellowLight?1:0), (byte)(redLight?1:0)};

        //send data
        try {
            radio.readWrite(RadioWriter.TARGET.CONSOLE_LEFT, to_send, to_send);
        } catch (RadioWriter.RadioException r)
        {
            tries += 1;
            System.out.println("Lost contact with Console_Left: " + tries);
            return;
        }
        catch (SerialException e)
        {
            System.out.println("Lost contact with serial monitor.");
            return;
        }

        //unpack received data
        for (int i=0; i < 1; i++) dials[i] = to_send[i];
        for (int i=0; i < 3; i++) switches[i] = to_send[i+3]>0;
        key = to_send[7]>0;
        yellowPushed = to_send[8]>0;
        redPushed = to_send[9]>0;
    }
}
