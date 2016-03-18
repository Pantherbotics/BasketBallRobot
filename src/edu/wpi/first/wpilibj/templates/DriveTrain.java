/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationEnhancedIO.EnhancedIOException;
import edu.wpi.first.wpilibj.DriverStationLCD;
/**
 *
 * @author Robotics Club
 */
public class DriveTrain extends Thread {
    Sensorimotor sm = new Sensorimotor();
    artillery art = new artillery();
    int driveMode = 0;//2;
    int numDriveM = 4;
    //  0: Holonomic
    //  1: Tank
    //  2: Holonomic Enhanced
    //  3: Tank Enhanced
    //  Default: 0

    public DriveTrain(){
        super();
    }
    public void run(){
        while(true){
//            art.launch(2.2);
            try {
//                sm.dseio.getFirmwareVersion();
//                sm.dseio.setLEDs((byte) 5);
//                sm.dseio.setLED(4, true);
                sm.dseio.setDigitalOutput(13, true); //White is plugged into this, black is ground
            } catch (Exception ex) {
                System.out.println("Error::"+ex);
            }
            //
            switch(driveMode){
                /*DO NOT RE-ARANGE CASE NUMBERS!*/
                case 0:
                    //Normal Holonomic
                    Main.driveMODE = "  Normal Holonomic";
                    sm.HoloDrive(sm.m_rghtStick.getX(), -sm.m_rghtStick.getY(), sm.m_rghtStick.getZ(), true);
                    //.Holodrive(X value, Y value, Z value (or rotation), isNormal)
                    break;
                case 1:
                    //Normal Tank
                    Main.driveMODE = " Normal Tank Drive";
                    sm.TankDrive(sm.m_rghtStick.getY(), sm.m_leftStick.getY(), true);
                    //.TankDrive(Right Stick, Left Stick, isNormal);
                    break;
                case 2:
                    //Enhanced Holonomic
                    Main.driveMODE = "Enhanced Holonomic";
                    sm.HoloDrive(sm.m_rghtStick.getX(), -sm.m_rghtStick.getY(), sm.m_rghtStick.getZ(), false);
                    //.Holodrive(X value, Y value, Z value (or rotation), isNormal)
                    break;
                case 3:
                    //Enhanced Tank
                    Main.driveMODE = "Enhanced TankDrive";
                    sm.TankDrive(sm.m_rghtStick.getY(), sm.m_leftStick.getY(), false);
                    //.TankDrive(Right Stick, Left Stick, isNormal);
                    break;
                default:
                    driveMode = driveMode - 4;
                    break;
            }
            if(sm.m_rghtStick.getRawButton(12)){
                // Cycle Drive TYPE (Holonomic or Tank)
                while(sm.m_rghtStick.getRawButton(12));
                driveMode++;
//                sm.hasRUN = false;
                //driveMode > number_of_drive_modes-1
                while(driveMode >= numDriveM) driveMode = driveMode - numDriveM;
            }
            if(sm.m_rghtStick.getRawButton(10)){
                //Cycle Drive MODE (Enhanced or Normal)
                while(sm.m_rghtStick.getRawButton(10));
                driveMode++;
                driveMode++;
//                sm.hasRUN = false;
                //driveMode > number_of_drive_modes-1
                while(driveMode >= numDriveM) driveMode = driveMode - numDriveM;
            }
        }
    }//end of Run()
}
