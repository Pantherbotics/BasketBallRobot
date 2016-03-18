/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.CANJaguar;
//import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 * @author Robotics Club
 */
public class CANJaguarWrapper {
    private CANJaguar CANObject;
//    private Jaguar backupObject;
//    private boolean errored;

    public CANJaguarWrapper(int node, CANJaguar.ControlMode Mode) {
        try {
            CANObject = new CANJaguar(node, Mode);
//            errored = false;
        } catch (Exception ex) {
            System.out.println("Error on CAN " + node + "::" + ex);
//            errored = true;
//            backupObject = new Jaguar(node);
        }
    }

    public CANJaguar getCANObject(){
        return CANObject;
    }

    public double getTemperature() {
        double returned = 0;
        try {
            returned = CANObject.getTemperature();
        } catch (Exception ex) {
            System.out.println("Error in getTemperature::" + CANObject + ex);
        }
        return returned;
    }

    public int getFirmwareVersion() {
        int returned = 0;
        try {
            returned = CANObject.getFirmwareVersion();
        } catch (Exception ex) {
            System.out.println("Error in getFirmwareVersion::" + CANObject + ex);
        }
        return returned;
    }

    void setPositionReference(CANJaguar.PositionReference reference)  {
        try {
            CANObject.setPositionReference(reference);
        } catch (Exception ex) {
            System.out.println("Error in setPositionReference::" + CANObject + ex);
        }
    }

    public double getPosition() {
        double returned = -1;
        try {
            returned = CANObject.getPosition();
        } catch (Exception ex) {
            System.out.println("Error in getPosition::" + CANObject + ex);
        }
        return returned;
    }

    public double getP() {
        double returned = -1;
        try {
            returned = CANObject.getP();
        } catch (Exception ex) {
            System.out.println("Error in getP::" + CANObject + ex);
        }
        return returned;
    }

    public double getI() {
        double returned = -1;
        try {
            returned = CANObject.getI();
        } catch (Exception ex) {
            System.out.println("Error in getI::" + CANObject + ex);
        }
        return returned;
    }

    public double getD() {
        double returned = -1;
        try {
            returned = CANObject.getD();
        } catch (Exception ex) {
            System.out.println("Error in getD:" + CANObject + ex);
        }
        return returned;
    }

    public double getSpeed() {
        double returned = -1;
        try {
            returned = CANObject.getSpeed();
        } catch (Exception ex) {
            System.out.println("Error in getSpeed::" + CANObject + ex);
        }
        return returned;
    }

    void setPID(double p, double i, double d)  {
        try {
            CANObject.setPID(p, i, d) ;
        } catch (Exception ex) {
            System.out.println("Error in setPID::" + CANObject + ex);
        }
    }

    void enableControl(double encoderInitialPosition) {
        try {
            CANObject.enableControl(encoderInitialPosition);
        } catch (Exception ex) {
            System.out.println("Error in enableControl::" + CANObject + ex);
        }
    }

    void configEncoderCodesPerRev(int codesPerRev) {
        try {
            CANObject.configEncoderCodesPerRev(codesPerRev);
        } catch (Exception ex) {
            System.out.println("Error in configEncoderCodesPerRev::" + CANObject + ex);
        }
    }
    //setSpeedReference(CANJaguar.SpeedReference reference)

    void setSpeedReference(CANJaguar.SpeedReference reference) {
        try {
            CANObject.setSpeedReference(reference);
        } catch (Exception ex) {
            System.out.println("Error in setSpeedReference::" + CANObject + ex);
        }
    }

    void setX(double speed) {
        try {
            CANObject.setX(speed);
        } catch (Exception ex) {
            System.out.println("Error in setX::" + CANObject + ex);
        }
    }
}
