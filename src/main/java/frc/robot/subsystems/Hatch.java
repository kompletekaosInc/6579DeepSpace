package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Hatch implements SubSystem {



    private DoubleSolenoid shooter;
    //private DoubleSolenoid bottomShooter;

    public Hatch(){

        shooter = new DoubleSolenoid(0,1);
        //bottomShooter = new DoubleSolenoid(2,3);
        //shooter.set(DoubleSolenoid.Value.kOff);
    }

    /**
     * shoots the hatch off
     */
    public void shootHatch(){
        shooter.set(DoubleSolenoid.Value.kForward);
    }

    /**
     * retracts the hatch panel launcher
     */
    public void retractHatch(){
        shooter.set(DoubleSolenoid.Value.kReverse);
    }



    @Override
    public void publishStats() {

    }

    @Override
    public void test() {

    }
}
