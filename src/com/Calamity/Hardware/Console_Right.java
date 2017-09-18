package com.Calamity.Hardware;

import com.Calamity.Color;

import javax.sql.rowset.serial.SerialException;

public class Console_Right implements Prop {

    public boolean[] switches;
    public byte sliders[];
    public boolean engines[];
    public boolean key;
    public short clock1;
    public short clock2;
    private String text;
    public Color[] ring;
    private RadioWriter radio;
    private int tries;

    public static Console_Right instance;

    byte data[];

    public Console_Right(RadioWriter r)
    {
        if (instance != null)
        {
            System.out.println("Console_Right already exists.");
            return;
        }

        radio = r;
        switches = new boolean[3];
        sliders = new byte[2];
        ring = new Color[24];
        for (int i=0; i < 24; i++) ring[i] = new Color(Color.COLOR.Red);
        engines = new boolean[] {true,false,true,false,true,true};
        text = "This is a test string just for this program.";
        data = new byte[32];

        instance = this;
    }

    private void send_ring() throws SerialException, RadioWriter.RadioException
    {
        for (int i=0; i < 24; i++) {
            data[i] = ring[i].get();
        }
        data[31] = 1;
        radio.readWrite(RadioWriter.TARGET.CONSOLE_RIGHT, data, data);
    }

    private void send_info() throws SerialException, RadioWriter.RadioException
    {
        data[0] = (byte)(clock1 / 256);
        data[1] = (byte) (clock1 % 256);
        data[2] = (byte) (clock2 / 256);
        data[3] = (byte) (clock2 % 256);
        for (int i=0; i < 6; i++)   data[i+4] = (byte)(engines[i]?0:1);
        data[31] = 2;
        radio.readWrite(RadioWriter.TARGET.CONSOLE_RIGHT,data,data);
    }

    public void setText(String t) throws IllegalArgumentException
    {
        if (t.length() > 32) throw new IllegalArgumentException("String too long.");
        text = String.format("%-" + 32 + "." + 32 + "s", t);
    }

    private void send_text() throws SerialException, RadioWriter.RadioException
    {
        byte[] d = text.getBytes();
        int i=0;
        for (; i < 32; i++) {
            data[i] = d[i];
        }
        if (data[31] == 1 || data[31] == 2) data[31] = 0;
        radio.readWrite(RadioWriter.TARGET.CONSOLE_RIGHT,data,data);
    }

    public void Update()
    {
        try
        {
            send_ring();
            //send_info();
            //send_text();
        } catch (RadioWriter.RadioException r)
        {
            tries += 1;
            System.out.println("Lost contact with Console_Right: " + tries);
            return;
        }
        catch (SerialException e)
        {
            System.out.println("Lost contact with serial monitor.");
            return;
        }

        switches[0] = data[0]>0;
        switches[1] = data[1]>0;
        switches[2] = data[2]>0;
        sliders[0] = data[3];
        sliders[1] = data[4];
    }
}
