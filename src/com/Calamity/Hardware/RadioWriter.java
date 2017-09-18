package com.Calamity.Hardware;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import javax.sql.rowset.serial.SerialException;
import java.sql.Time;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

public class RadioWriter {
    SerialPort serialPort;
    boolean test;
    /** The port we're normally going to use. */
    private static final String PORT_NAMES[] = {"COM3", // Windows
    };

    private BufferedInputStream input;
    private OutputStream output;
    /** Milliseconds to block while waiting for port open */
    private static final int TIME_OUT = 2000;
    /** Default bits per second for COM port. */
    private static final int DATA_RATE = 115200;

    public enum TARGET {
        MASTER, CONSOLE_LEFT, CONSOLE_CENTER, CONSOLE_RIGHT, PIPES
    };

    public RadioWriter()
    {
        test = false;
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        //First, Find an instance of serial port as set in PORT_NAMES.
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    break;
                }
            }
        }
        if (portId == null) {
            System.out.println("Could not find COM port.");
            return;
        }

        try {
            // open serial port, and use class name for the appName.
            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                    TIME_OUT);

            // set port parameters
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            serialPort.disableReceiveTimeout();
            serialPort.enableReceiveThreshold(1);


            // open the streams
            input = new BufferedInputStream(serialPort.getInputStream());

            output = serialPort.getOutputStream();

            while (input.available() == 0) {
                TimeUnit.SECONDS.sleep(2);
            }
            System.out.println(input.read());

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public class RadioException extends Exception
    {

    }

    private void checkReply(byte[] data) throws RadioException
    {
        boolean noAck = true;
        boolean noReply = true;
        for (int i=0; i < 8; i++)
        {
            if (data[i] != 1) {
                noAck = false;
            }
            if (data[i] != 2) {
                noReply = false;
            }
            if (!(noAck || noReply)) break;
        }
        if (noAck) throw new RadioException();
        if (noReply) throw new RadioException();
    }

    public RadioWriter(boolean test)
    {
        this.test = test;
    }

    /**
     *
     * @param t The prop to which to write
     * @param to_send The data to be written
     * @param received Storage for the data read back
     * @throws Exception
     */
    public void readWrite(TARGET t, byte[] to_send, byte[] received) throws IllegalArgumentException, SerialException, RadioException
    {
        if (to_send.length > 32) {
            throw new IllegalArgumentException("Bad data array.");
        }

        if (test)
        {
            for (int i=0; i < 32; i++) received[i] = (byte)i;
            return;
        }
        try {
            output.write(t.ordinal());
            output.write(to_send);
            if (to_send.length < 32) {
                output.write(new byte[32 - to_send.length]);
            }
            int x = input.read(received, 0, 32);
            if (x < 32) {
                throw new SerialException();
            }
            checkReply(received);
        }
        catch (IOException e)
        {
            throw new SerialException();
        }
    }

}