package frc.robot.control;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
//import frc.robot.subsystems.Drivetrain;
//import frc.robot.subsystems.Drivetrain;

public class DriverControls extends PSController {

    public DriverControls() {
        super(0);
    }

    //private Drivetrain drivetrain =

    /**

     */
    private void arcadeDrive(Robot robot){
        double stickX = controller.getRawAxis(0);
        double stickY = controller.getRawAxis(1);

        robot.getDrivetrain().arcadeDiffDrive(stickX,stickY);

    }

    /**
     * This controls the robot and should only be used during TeleOp
     * @param robot
     */
    @Override
    public void giveCommands(Robot robot) {
        super.giveCommands(robot);
        arcadeDrive(robot);
    }

    @Override
    protected void processCross(Robot robot) {
        //super.processCross(robot);
        robot.getCarriage().launcherOut();
    }

    @Override
    protected void processSquare(Robot robot) {
        //super.processSquare(robot);
        //robot.getLift().liftUp(1);
    }

    @Override
    protected void processCircle(Robot robot) {
        //super.processCircle(robot);
        //robot.getLift().liftDown(0.5);
    }

    @Override
    protected void processTriangle(Robot robot) {
        //super.processTriangle(robot);
        robot.getIntake().out();
    }

    @Override
    protected void processL1(Robot robot) {
        //super.processL1(robot);
        robot.getIntake().in();
        robot.getCarriage().flywheelIn();
    }

    @Override
    protected void processR1(Robot robot) {
        //super.processR1(robot);
        robot.getCarriage().flywheelSpill();
    }

    @Override
    protected void processL2(Robot robot) {
        //super.processL2(robot);
        robot.getHatch().shootHatch();
    }

    @Override
    protected void processR2(Robot robot) {
        //super.processR2(robot);
        robot.getCarriage().flywheelOutHigh();
    }

    //Hatch is front
    @Override
    protected void processShare(Robot robot) {
        //super.processShare(robot);
        robot.getDrivetrain().setCargoToFront();
      //  SmartDashboard.putString("Which subsystem is at front", "Cargo is the front");
        //robot.getIntake().up();
    }

    //Cargo is front
    @Override
    protected void processOptions(Robot robot) {
        //robot.getIntake().down();
        robot.getDrivetrain().setHatchToFront();
      //  SmartDashboard.putString("Which subsystem is at front", "Hatch is the front");
    }

    @Override
    protected void processNoButtons(Robot robot) {
        //super.processNoButtons(robot);

        robot.getHatch().retractHatch();
        robot.getCarriage().launcherIn();
        robot.getCarriage().stop();
        //robot.getIntake().hold();
        robot.getIntake().stopRollers();
        //robot.getLift().stopLift();
        //robot.getIntake().hold();
        //robot.getLift().hold();


    }
}
