package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;

public class Carriage implements SubSystem {


    private DoubleSolenoid carriageLauncher;

    private VictorSP carriageFlywheel;

    private static double suckInSpeed = 0.45;

    private static double shootHighSpeed = -0.6;

    private static double spillSpeed = -0.3;

    public Carriage(){

        carriageFlywheel = new VictorSP(4);

        carriageLauncher = new DoubleSolenoid(2,3);
        //carriageLauncher.set(DoubleSolenoid.Value.kOff);

    }

    /**
     * sucks the ball in at the set speed defined in the constructor
     */
    public void flywheelIn(){
        carriageFlywheel.set(suckInSpeed);
    }

    /**
     * Shoots the ball out (set for the high things, not for cargo ship)
     */
    public void flywheelOutHigh(){
        carriageFlywheel.set(shootHighSpeed);
    }

    /**
     * Retracts the cylinder
     */
    public void launcherIn(){
        carriageLauncher.set(DoubleSolenoid.Value.kReverse);
    }

    /**
     * Shoots out the cylinder
     */
    public void launcherOut(){
        carriageLauncher.set(DoubleSolenoid.Value.kForward);
    }

    /**
     * spills the ball out at an ideal speed for the cargo ship
     */
    public void flywheelSpill(){
        carriageFlywheel.set(spillSpeed);
    }

    public void stop(){
        carriageFlywheel.set(0);
    }


    @Override
    public void publishStats() {

    }

    @Override
    public void test() {

    }
}
