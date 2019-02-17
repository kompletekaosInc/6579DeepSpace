package frc.robot.subsystems;

/*
Subsystem interface is used to define a subsystem.

Created 9/2/19 by Jiah
 */

public interface SubSystem {

    /*
    This is an interface to define the structure of a robot subsystem.
     */

    /*
    All subsystems should be able to publish any relevant statistics in regards to their datasets to the smart(shuffle) board
     */
    public void publishStats();

    /*
    all systems can utilise this to test functionality in test mode.
     */
    public void test();
}
