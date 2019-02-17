package frc.robot.control;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Robot;

public class OperatorControls extends PSController {


    private Joystick controller1;
    public OperatorControls() {
        super(1);

        controller1 = new Joystick(1);
    }

    /**
     * This controls the robot and should only be used during TeleOp
     * @param robot
     */
    @Override
    public void giveCommands(Robot robot) {
        super.giveCommands(robot);
//        liftManual(robot);
//        armManual(robot);

    }

//    private void liftManual(Robot robot){
//        robot.getLift().liftSet(controller1.getRawAxis(1));
//    }
//    private void armManual(Robot robot){
//        //robot.getIntake().(controller1.getRawAxis(1));
//        if (controller1.getRawAxis(5)>=0){
//            robot.getIntake().up();
//        }
//        else{
//            robot.getIntake().down();
//        }
//    }

    @Override
    protected void processCross(Robot robot) {

    }

    @Override
    protected void processSquare(Robot robot) {

    }

    @Override
    protected void processCircle(Robot robot) {

    }

    @Override
    protected void processTriangle(Robot robot) {
        //top ball
    }

    @Override
    protected void processL1(Robot robot) {
        robot.getIntake().up();
    }

    @Override
    protected void processR1(Robot robot) {
        robot.getIntake().down();
    }

    @Override
    protected void processL2(Robot robot) {
        robot.getLift().liftUp(1);
    }

    @Override
    protected void processR2(Robot robot) {
        robot.getLift().liftDown(0.4);
    }

    @Override
    protected void processShare(Robot robot) {

    }

    @Override
    protected void processOptions(Robot robot) {

    }

    @Override
    protected void processNoButtons(Robot robot) {
        robot.getIntake().stopArm();
        robot.getLift().hold();
    }
}
