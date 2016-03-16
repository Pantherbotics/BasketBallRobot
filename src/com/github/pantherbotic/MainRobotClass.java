package com.github.pantherbotic;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;

public class MainRobotClass extends IterativeRobot {

    public final Jaguar motoBackLeft = new Jaguar(1, 1), motoFrontLeft = new Jaguar(1, 2), motoFrontRight = new Jaguar(1, 3), motoBackRight = new Jaguar(1, 4), motoShoot1 = new Jaguar(1, 5), motoShoot2 = new Jaguar(1, 6);
    public final Victor motoPickUp = new Victor(1, 7), motoBelt = new Victor(1, 8);
    public final Joystick rightJoystick = new Joystick(1);

    public void teleopPeriodic() {
        double xAxis = rightJoystick.getX(), yAxis = rightJoystick.getY(), twist = rightJoystick.getTwist(), shooterSpeed = (rightJoystick.getThrottle() + 1) / 2;

        boolean trigger = rightJoystick.getTrigger();

        this.motoBackLeft.set(xAxis - yAxis + twist);
        this.motoFrontLeft.set(-xAxis - yAxis + twist);
        this.motoFrontRight.set(-xAxis + yAxis + twist);
        this.motoBackRight.set(-xAxis + yAxis + twist);

        this.motoBelt.set(trigger ? 1 : 0);
        this.motoPickUp.set(rightJoystick.getRawButton(7) ? 1 : 0);

        this.motoShoot1.set(shooterSpeed);
        this.motoShoot2.set(-shooterSpeed);
    }
}
