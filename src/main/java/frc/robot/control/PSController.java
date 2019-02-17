package frc.robot.control;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robot;

public abstract class PSController implements RobotControl {

    //protected DriverControl driverControl;

    protected Joystick controller;

    public PSController(int port){

        controller = new Joystick(port);

//        JoystickButton Square = new JoystickButton(controller, 1);
//        JoystickButton X = new JoystickButton(controller,2);
//        JoystickButton O = new JoystickButton(controller,3);
//        JoystickButton Triangle = new JoystickButton(controller,4);
//        JoystickButton L1 = new JoystickButton(controller,5);
//        JoystickButton R1 = new JoystickButton(controller,6);
//        JoystickButton L2 = new JoystickButton(controller,7);
//        JoystickButton R2 = new JoystickButton(controller, 8);
//        JoystickButton Share = new JoystickButton(controller,9);
//        JoystickButton Options = new JoystickButton(controller, 10);
//        JoystickButton LeftStick = new JoystickButton(controller, 11);
//        JoystickButton RightStick = new JoystickButton(controller, 12);
//        JoystickButton PS = new JoystickButton(controller,13);
//        JoystickButton Touchpad = new JoystickButton(controller,14);


    }

    @Override
    public void giveCommands(Robot robot) {
        processStickButton(robot);

    }

    private void processStickButton(Robot robot) {

        if (controller.getRawButton(1)) {
            processSquare(robot);
        } else if (controller.getRawButton(2)) {
            processCross(robot);
        } else if (controller.getRawButton(3)) {
            processCircle(robot);
        } else if (controller.getRawButton(4)) {
            processTriangle(robot);
        } else if (controller.getRawButton(5)) {
            processL1(robot);
        } else if (controller.getRawButton(6)) {
            processR1(robot);
        } else if (controller.getRawButton(7)) {
            processL2(robot);
        } else if (controller.getRawButton(8)) {
            processR2(robot);
        } else if (controller.getRawButton(9)) {
            processShare(robot);
        } else if (controller.getRawButton(10)) {
            processOptions(robot);
        } else if (controller.getRawButton(11)) {
            processLStick(robot);
        } else if (controller.getRawButton(12)) {
            processRStick(robot);
        } else if (controller.getRawButton(13)){
            processPSButton(robot);
        } else if (controller.getRawButton(14)){
            processTouchpad(robot);
        } else {
            processNoButtons(robot);
        }
    }

    /**
     * Process any specific events for button 1 on the joystick.
     *
     * @param robot
     */
    protected void processSquare(Robot robot) {
        // This is the opportunity for a subclass to create button one
    }

    /**
     * Process any specific events for button 2 on the joystick.
     *
     * @param robot
     */
    protected void processCross(Robot robot) {
        // This is the opportunity for a subclass to create button two
    }

    /**
     * Process any specific events for button 3 on the joystick.
     *
     * @param robot
     */
    protected void processCircle(Robot robot) {
        // This is the opportunity for a subclass to create button three
    }

    /**
     * Process any specific events for button 4 on the joystick.
     *
     * @param robot
     */
    protected void processTriangle(Robot robot) {
        // This is the opportunity for a subclass to create button four
    }

    /**
     * Process any specific events for button 5 on the joystick.
     *
     * @param robot
     */
    protected void processL1(Robot robot) {
        // This is the opportunity for a subclass to create button five
    }

    /**
     * Process any specific events for button 6 on the joystick.
     *
     * @param robot
     */
    protected void processR1(Robot robot) {
        // This is the opportunity for a subclass to create button six
    }

    /**
     * Process any specific events for button 7 on the joystick.
     *
     * @param robot
     */
    protected void processL2(Robot robot) {
        // This is the opportunity for a subclass to create button seven
    }

    /**
     * Process any specific events for button 8 on the joystick.
     *
     * @param robot
     */
    protected void processR2(Robot robot) {
        // This is the opportunity for a subclass to create button eight
    }

    /**
     * Process any specific events for button 9 on the joystick.
     *
     * @param robot
     */
    protected void processShare(Robot robot) {
        // This is the opportunity for a subclass to create button nine
    }

    /**
     * Process any specific events for button 10 on the joystick.
     *
     * @param robot
     */
    protected void processOptions(Robot robot) {
        // This is the opportunity for a subclass to create button ten
    }

    /**
     * Process any specific events for button 11 on the joystick.
     *
     * @param robot
     */
    protected void processLStick(Robot robot) {
        // This is the opportunity for a subclass to create button eleven
    }

    /**
     * Process any specific events for button 12 on the joystick.
     *
     * @param robot
     */
    protected void processRStick(Robot robot) {
        // This is the opportunity for a subclass to create button twelve
    }

    protected void processPSButton(Robot robot){
        //Do stuff for button 13
    }

    protected void processTouchpad(Robot robot){
        //do stuff button 14
    }

    /**
     * Process any specific events for no buttons on the joystick.
     *
     * @param robot
     */
    protected void processNoButtons(Robot robot) {
        // This is the opportunity for a subclass to create no button
    }


}
