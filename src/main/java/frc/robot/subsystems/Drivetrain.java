package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.logging.Logger;

public class Drivetrain implements SubSystem {

    private boolean cargoIsFront = true;


    private VictorSP leftTB1 = new VictorSP(0);
    private VictorSP leftTB2 = new VictorSP(1);
    private SpeedControllerGroup toughBox1 = new SpeedControllerGroup(leftTB1, leftTB2);;
    private SpeedControllerGroup leftTB = toughBox1;  // default startr game toughbox1 is the leftTB


    private VictorSP rightTB1 = new VictorSP(2);
    private VictorSP rightTB2 = new VictorSP(3);
    private SpeedControllerGroup toughBox2 = new SpeedControllerGroup(rightTB1, rightTB2);;
    private SpeedControllerGroup rightTB = toughBox2;

    // init the drivetrain
    private DifferentialDrive robotDrive = new DifferentialDrive(leftTB, rightTB);

    private Compressor compressor;

    //private UsbCamera camera1 = null;

    private Logger logger = Logger.getLogger( this.getClass().getName());


    public Drivetrain(){
        compressor = new Compressor(0);
        compressor.setClosedLoopControl(true);

//        try {
//            camera1 = CameraServer.getInstance().startAutomaticCapture();
//        } catch (Exception e) {
//            logger.info("Camera not installed correctly" + e.toString());
//            //SmartDashboard.putBoolean("Camera Installed", false);
//        }



        //TODO: Find out what other methods belong to the drivetrain

    }

    /*
     * this is the method for moving the robot, could be used by arcade drive and
     * auto
     *
     * @param leftPower
     *
     * @param rightPower
     */
    public void setPower(double leftPower, double rightPower) {
        // sets the left toughbox
        leftTB.set(-leftPower);
        // sets the right toughbox
        rightTB.set(rightPower);
        //SmartDashboard.putNumber("Power", (rightPower + leftPower) / 2);
    }

    public void arcadeDiffDrive(double stickX, double stickY) {

        //SmartDashboard.getNumber("Stick X: ", stickX);
        //SmartDashboard.getNumber("Stick Y: ", stickY);
        if(cargoIsFront) {
            robotDrive.arcadeDrive(stickY, stickX);
        } else {
            robotDrive.arcadeDrive(-stickY, stickX);
        }
    }

    public void setCargoToFront(){
        cargoIsFront = true;

    //    leftTB = toughBox1;
    //    rightTB = toughBox2;

        //robotDrive = new DifferentialDrive(leftTB, rightTB);

    }

    public void setHatchToFront(){
        cargoIsFront = false;

   //     leftTB = toughBox2;
   //     rightTB = toughBox1;

        //robotDrive = new DifferentialDrive(leftTB, rightTB);
    }







    @Override
    public void publishStats() {
        //fill with stats to put on the shuffleboard/smartdash/ etc
        SmartDashboard.putBoolean("cargoIsFront", cargoIsFront);
    }

    @Override
    public void test() {

    }
}
