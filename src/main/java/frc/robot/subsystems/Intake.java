package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;

public class Intake implements SubSystem {


    private VictorSP armMotor;
    private VictorSP rollers;

    private static double inSpeed = -0.5;
    private static double outSpeed = 0.5;

    private static double upSpeed = -0.6;
    private static double downSpeed = 0.6;

    private boolean isUp = true;

    /**
     * This constructor assigns the ports for all the motors in the intake subsystem
     */
    public Intake(){

        armMotor = new VictorSP(6);
        rollers = new VictorSP(5);

    }

    /**
     * drives the arm up at the designated up speed
     */
    public void up(){
        armMotor.set(upSpeed);
        isUp = true;
    }

    /**
     * same as up but opposite direction
     */
    public void down(){
        armMotor.set(downSpeed);
        isUp = false;
    }

    /**
     * spins the rollers so they suck the ball in at the designated speed
     */
    public void in(){
        rollers.set(inSpeed);
    }

    /**
     * sets the rollers to push a ball out, in case of jamming.
     */
    public void out(){
        rollers.set(outSpeed);
    }

    /**
     * sets all powers in the system to 0
     */
    public void stopRollers(){
        rollers.set(0);
        //armMotor.set(0);
    }

    public void stopArm(){
        armMotor.set(0);
    }

    public void hold(){
        if (isUp){
            armMotor.set(-0.05);
        }else{
            armMotor.set(0.05);
        }
    }





    @Override
    public void publishStats() {

    }

    @Override
    public void test() {

    }
}
