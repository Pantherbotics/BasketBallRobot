package com.github.pantherbotic;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;

public class MainRobotClass extends IterativeRobot {

    /*
    * Basic code instantiating a Jaguar(module, port).  Note that the module is base 1, and is the number of that module type (Digital, Analog, Etc.)
    */
    public final Jaguar motoBackLeft = new Jaguar(1, 1), motoFrontLeft = new Jaguar(1, 2), motoFrontRight = new Jaguar(1, 3), motoBackRight = new Jaguar(1, 4), motoShoot1 = new Jaguar(1, 5), motoShoot2 = new Jaguar(1, 6);
    /*
    * Basic code instantiating a Victor(module, port).  Follows the same conventions as Jaguars
    */
    public final Victor motoPickUp = new Victor(1, 7), motoBelt = new Victor(1, 8);
    /*
    * A Class/Object that handles Joystick(port[base1, set in DS]) data. 
    */
    public final Joystick rightJoystick = new Joystick(1);

    /*
    * Called 60 times a second (i think)
    */
    public void teleopPeriodic() {
        /*
        * Sets varaibles to corresponding data.  Done at start so as to make it more efficient to access these values later.
        * The shooter essentially normalizes data from -1 to 1 into 0 to 1.
        */
        double xAxis = rightJoystick.getX(), yAxis = rightJoystick.getY(), twist = rightJoystick.getTwist(), shooterSpeed = (rightJoystick.getThrottle() + 1) / 2;

        /*
        * A boolean variable thats true if the trigger is in, false if it isn't.
        */
        boolean trigger = rightJoystick.getTrigger();

        /*
        * Sets the motors after running values through a basic omniwheel algorithm (signs might be tweaked)
        */
        this.motoBackLeft.set(xAxis - yAxis + twist);
        this.motoFrontLeft.set(-xAxis - yAxis + twist);
        this.motoFrontRight.set(-xAxis + yAxis + twist);
        this.motoBackRight.set(-xAxis + yAxis + twist);

        /*
        * Sets the belt and pickup based on if the various buttons where pressed 
        */
        this.motoBelt.set(trigger ? 1 : 0);
        this.motoPickUp.set(rightJoystick.getRawButton(7) ? 1 : 0);

        /*
        * Sets shooter wheel speed to the correct normalized values
        */
        this.motoShoot1.set(shooterSpeed);
        this.motoShoot2.set(-shooterSpeed);
    }
}
