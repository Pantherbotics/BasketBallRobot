/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package com.github.pantherbotic;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class MainRobotClass extends IterativeRobot {

    public static final Jaguar motoBackLeft = new Jaguar(1, 1);
    public static final Jaguar motoFrontLeft = new Jaguar(1, 2);
    public static final Jaguar motoFrontRight = new Jaguar(1, 3);
    public static final Jaguar motoBackRight = new Jaguar(1, 4);
    public static final Joystick leftJoystick = new Joystick(0);
    public static final Joystick rightJoystick = new Joystick(1);

    public void teleopPeriodic() {
        double xAxis = rightJoystick.getX(),
                yAxis = rightJoystick.getY(),
                twist = rightJoystick.getTwist(),
                throttle = rightJoystick.getThrottle();

        motoBackLeft.set(xAxis + yAxis - twist);
        motoFrontLeft.set(-xAxis + yAxis - twist);
        motoFrontRight.set(-xAxis - yAxis - twist);
        motoBackRight.set(-xAxis - yAxis - twist);
    }
}
