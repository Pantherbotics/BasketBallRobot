/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Main extends IterativeRobot {
    String George = "George";
    private static class Robot {
        private static void kill(String name) {
            System.out.println("Robot initializing to kill "+name+"...");
        }
        private static void printDS(String methd, String other){
            /*
             * Verticle (up/down), Horizontal (Right/Left)
             * .println(verticle line, horizontal collum (tab), string to display);
            dashboard.println(DriverStationLCD.Line.kMain6, 1, "kMain6");
            dashboard.println(DriverStationLCD.Line.kUser2, 2, "kUser2");
            dashboard.println(DriverStationLCD.Line.kUser3, 3, "kUser3");
            dashboard.println(DriverStationLCD.Line.kUser4, 4, "kUser4");
            dashboard.println(DriverStationLCD.Line.kUser5, 5, "kUser5");
            dashboard.println(DriverStationLCD.Line.kUser6, 6, "kUser6");
             *
             * Above Displayes as Bellow on dashboard...
             *
             ************************************************
             * kMain6                                       *
             *  kUser2                                      *
             *   kUser3                                     *
             *    kUser4                                    *
             *     kUser5                                   *
             *      kUser6                                  *
             ************************************************
             */
            dashboard.free(); //Unknown? Doesn't hurt.
            dashboard.println(DriverStationLCD.Line.kMain6, 4, "Drive Method:");
            dashboard.println(DriverStationLCD.Line.kUser2, 1, methd);
            String blank = "            ";
            dashboard.updateLCD(); //Always updateLCD() every loop or you will NOT see changes!
        }
    }
    /**
     * This area is for any initialization code.
     * Initialization code initializes, or tell the code, what exists on the robot.
     * AKA
     * This is where we tell the code we have a Jaguar called Moto1 in Slot 1
     */
    static DriverStationLCD dashboard = DriverStationLCD.getInstance();
    static String driveMODE = "Not Initialized...";
    static String Info;
    DriveTrain driveThread;
    artillery art;
    Sensorimotor sm = new Sensorimotor();
    boolean isDriveTrainThreadAlive = false;
    /*** IMPORTANT ***
     * Use above variable to check if DriveTrainThread has been started
     * DO NOT LET THREADS START AGAIN, AFTER THEY HAVE STARTED THE 1ST TIME
     */
    public void robotInit() {
        //N-A
    }
    public void autonomousPeriodic() {
        // Start of Automatic-Control
    }

    /*
     * NOTE *
     *   Wireless router/bridge must be on "AP" (acess point) mode!
     * To switch modes, move the switch on the back of the router to "AP"
     */

    public void teleopPeriodic() {
        // Start of User-Control
        Robot.printDS(driveMODE, Info);
        if (!isDriveTrainThreadAlive){
            //Run ONCE - starts a THREAD
            Robot.kill(George);
            driveThread = new DriveTrain();
            driveThread.start();
            art = new artillery();
            art.start();
            isDriveTrainThreadAlive = true;
        }
    }
}
