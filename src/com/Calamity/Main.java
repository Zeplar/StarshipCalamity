package com.Calamity;

import com.Calamity.Component.Test_Shield;
import com.Calamity.GUI.Display_Console;
import com.Calamity.GUI.Display_Console_Center;
import com.Calamity.GUI.Display_Console_Right;
import com.Calamity.Hardware.Console_Right;
import com.Calamity.Hardware.RadioWriter;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        int i=0;
        Display_Console_Center center = new Display_Console_Center();
        Display_Console_Right right = new Display_Console_Right();
        Display_Console console = new Display_Console(center, right);

    /*    long updateTime;
        RadioWriter radio = new RadioWriter();

        new Console_Right(radio);
        Test_Shield shield = new Test_Shield(300);
        long time = System.currentTimeMillis();

        Display_Console_Center.main();

        while (true)
        {
            updateTime = System.currentTimeMillis() - time;
            time = System.currentTimeMillis();
            shield.Update((int)updateTime);
            Console_Right.instance.Update();
        }

    }
/**
 Spaceship ship = new Spaceship();
 long updateTime = 0;
 while (true)
 {
 long time = System.currentTimeMillis();
 ship.Update((int)(time-updateTime));
 updateTime = time;
 }
**/
 }
}
