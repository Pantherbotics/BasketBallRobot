/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 *
 * @author Robotics Club
 */
public class Sensorimotor {
    //DIY Holonomic Drive variables
    //Left back
    private static double wheel1; // Motor speeds for wheels.
    //Left front
    private static double wheel2;
    //Right front
    private static double wheel3;
    //Right back
    private static double wheel4;
//    static Encoder testENC= new Encoder(1,2);

    DriverStationEnhancedIO dseio = DriverStation.getInstance().getEnhancedIO();
    public static boolean lghtSTAT;
//    public lghtSTAT = sm.dseio.getAnalogIn(2);

    //Jaguar Init
    CANJaguarWrapper moto1;
    CANJaguarWrapper moto2;
    CANJaguarWrapper moto3;
    CANJaguarWrapper moto4;

    boolean hasRUN = false;

    //Jaguars & Victors called from DigitalSideCar
    //see wheel1 through wheel4 above for motor positions
//    static Jaguar moto1 = new Jaguar(1,1);     // initialize Jaguar @ Module 1, Port 1
//    static Jaguar moto2 = new Jaguar(1,2);
//    static Jaguar moto3 = new Jaguar(1,3);
//    static Jaguar moto4 = new Jaguar(1,4);
//    static Jaguar shldr = new Jaguar(1,5);

//    public static int phTMP = 0;
//    public static boolean temp = false;

//    static DigitalInput limit_shldr_L = new DigitalInput(2,7); // Inputs for the limit switches on the shoulders?
//    static DigitalInput limit_shldr_H = new DigitalInput(2,6);

    //Joystick
    static Joystick m_leftStick = new Joystick(2);
    static Joystick m_rghtStick = new Joystick(1);

    public Sensorimotor() {
//        try {
//            leftJag = new CANJaguar(2);
//        } catch (CANTimeoutException ex) {
//            ex.printStackTrace();
//        }
    }

//    public int ButtonToggle(int numb, boolean press) {
//        if (press) {
//            phTMP++;
//        }
//        if (phTMP >= numb) {
//            phTMP = 0; //reset
//        }
//        return phTMP+1; //changes return from 0-(numb-1) to 1-numb
//    }

        double p = 2;
        double i = 0;
        double d = 0;
    public void HoloDrive(double xvalue,double yvalue,double rvalue, boolean isNormal) {
        if(!hasRUN){
            moto4 = new CANJaguarWrapper(4, CANJaguar.ControlMode.kSpeed);
            moto2 = new CANJaguarWrapper(2, CANJaguar.ControlMode.kPercentVbus);
            moto3 = new CANJaguarWrapper(3, CANJaguar.ControlMode.kPercentVbus);
            moto1 = new CANJaguarWrapper(1, CANJaguar.ControlMode.kPercentVbus);
            hasRUN= true;
//            moto4.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
            moto4.setSpeedReference(CANJaguar.SpeedReference.kEncoder);
            moto4.configEncoderCodesPerRev(400); //1000 for wheel, 400 for shooter
//            moto4.setPID(100, 0, 0);
            moto4.enableControl(0);
        }
        /*Start Normal Drive*/
        //Assumes positive motor speed is CLOCKWISE looking from motor to wheel
        //Y-axis is flipped -- joystick forward gives NEGATIVE value

        if (m_leftStick.getRawButton(6)){
            p = p + 1.0;
        } else if (m_leftStick.getRawButton(7)) {
            p = p - 1.0;
        }
        if (m_leftStick.getRawButton(4)){
            i = i + 0.010;
        } else if (m_leftStick.getRawButton(5)) {
            i = i - 0.010;
        }
        if (m_leftStick.getRawButton(11)){
            d = d + 0.010;
        } else if (m_leftStick.getRawButton(10)) {
            d = d - 0.010;
        }
        moto4.setPID(p, i, d);
        System.out.println("speed::" + (moto4.getSpeed()*4)+";enc::"+moto4.getPosition());
//        System.out.println("P::"+moto4.getP()+";I::"+moto4.getI()+";D::"+moto4.getD());

//        moto1.

        xvalue = MathUtils.pow(xvalue, 3)/2; // Why?
        yvalue = MathUtils.pow(yvalue, 3)/2;
        rvalue = MathUtils.pow(rvalue, 3)/2;

        //Left Back
        wheel1 = (-xvalue+yvalue+rvalue);

        //Left Front
        wheel2 = (xvalue+yvalue+rvalue);

        //Right Front
        wheel3 = (xvalue-yvalue+rvalue);

        //Right Back
        wheel4 = (-xvalue-yvalue+rvalue);

        if (m_rghtStick.getTrigger()){
            wheel1 = wheel1 * ((-m_rghtStick.getThrottle()+1)/2);
            wheel2 = wheel2 * ((-m_rghtStick.getThrottle()+1)/2);
            wheel3 = wheel3 * ((-m_rghtStick.getThrottle()+1)/2);
            wheel4 = wheel4 * ((-m_rghtStick.getThrottle()+1)/2);
        }
        /*End Normal Drive*/

        if (!isNormal){
            //Enhanced Holonomic Drive Mode
        }
               
        //UNCOMMENT
//        moto1.setX(wheel1);
        moto1.setX(-m_rghtStick.getX()/2);
        moto4.setX((m_rghtStick.getX()*1100));
//        moto2.setX(wheel2);
//        moto3.setX(wheel3);
//        moto4.setX(wheel4);
    }

    void TankDrive(double Lvalue, double Rvalue, boolean isNormal) {
        if(!hasRUN){
            moto1 = new CANJaguarWrapper(1, CANJaguar.ControlMode.kPercentVbus);
            moto2 = new CANJaguarWrapper(2, CANJaguar.ControlMode.kPercentVbus);
            moto3 = new CANJaguarWrapper(3, CANJaguar.ControlMode.kPercentVbus);
            moto4 = new CANJaguarWrapper(4, CANJaguar.ControlMode.kPercentVbus);
            hasRUN= true;
        }
        //Encoder attached to Jag#2
//        moto2.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
//        System.out.println("EncPose::"+moto2.getPosition());
        moto1.setX(Rvalue);
        moto2.setX(Rvalue);
        moto3.setX(Lvalue);
        moto4.setX(Lvalue);
    }
}