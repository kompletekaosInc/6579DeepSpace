/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.Solenoid;

/**
 * Add your docs here.
 */
public class Climber implements SubSystem{

    private DoubleSolenoid climber;

    public Climber(){
        
        climber = new DoubleSolenoid(4, 5);

    }

    /**
     * shoots the hatch off
     */
    public void liftRobot(){
        climber.set(DoubleSolenoid.Value.kForward);
    }

    /**
     * retracts the hatch panel launcher
     */
    public void retractCylinder(){
        climber.set(DoubleSolenoid.Value.kReverse);
    }

    @Override
    public void publishStats() {

    }

    @Override
    public void test() {

    }
}
