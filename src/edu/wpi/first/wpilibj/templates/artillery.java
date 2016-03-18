/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick.ButtonType;

/**
 *
 * @author Robotics Club
 */

/**
 * artillery.java
 * Aaron Zucherman
 * 2/2/12
 */

class artillery extends Thread {
    artillery art;
    Sensorimotor sm;

    static double TOP_HOOP = 98; //top hoop height (inches)
    static double MID_HOOP = 61; //middle hoop height (inches)
    static double BTM_HOOP = 28; //bottem hoop height (inches)
    static double s = 36; //robot height (inches)
    static double g = 32.174; //acceleration due to gravity
//    static double hoop = 2;
    static double o = 45; //70; (degrees)
    static double d = 30; //This will be determined by camera triginometry
    static double wdiameter = 8; //diameter of wheels in inches
    static double lspeed; //Launche speed (both wheels)
//    static double Tspeed; //speed for top wheel
//    static double Bspeed; //speed for btm wheel

    public artillery(){
        super();
    }
    public void run(){
        //Wait to fire
        while(0==0){
            //0 always = to 0, and makes a face :D
            if(sm.m_leftStick.getTrigger()){
                double d = 0;//get distance from camera
                launch(d, 1);
            }
        }
    }
    public static void launch(double d, int hoop) {
        double h;
        double v;
        if(hoop == 1) h = TOP_HOOP;
        else if(hoop == 2) h = MID_HOOP;
        else h = BTM_HOOP;

        /*
        * h = height of hoop
        * s = robot height
        * g = Acceleration of gravity
        * d = distance between gun tip and hoop
        * o = shooting angle
        * v = Velocity
        */
        if (d > 2*(s-h)/Math.tan(o)) {
            v = Math.sqrt(((-1)*g*(d*d))/((2*d*Math.sin(o)*Math.cos(o))+(2*(s-h)*(Math.cos(o)*Math.cos(o)))));
        } else {
            v = -1;
            // Too close to make the shot.
        }
        // Aaron's code
        //             if((2*height-2*distance*Math.tan(angle)) < 0) {
        //                 System.out.print("to close");
        //             } else {
        //                velocity = ((distance*Math.sqrt((GRAVITY/(2*height-2*distance*Math.tan(angle)))))/Math.cos(angle));
        //                System.out.print(velocity);
        //             }
        lspeed = v/(2*Math.PI*wdiameter);
        System.out.println("Wheel Speed::"+lspeed);
    }
}
