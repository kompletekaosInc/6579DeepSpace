package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static java.lang.Math.abs;

public class Lift implements SubSystem {

    private static final double HOLD_POWER = 0.03;


    private CANSparkMax spark7;
    private CANSparkMax spark8;

    private SpeedControllerGroup lift;

    private CANEncoder encoder1;
    //private CANEncoder encoder2;

    private static double cmToEncoderValues = 1.6233804196;
    private static double encoderToCmValue = 0.6159985595;

    private boolean isHolding = false;

    private double bottomValue;


    public Lift(){
        //Todo: Does the lift own the cameras? y/n

//        spark8 = new CANSparkMax(8, CANSparkMaxLowLevel.MotorType.kBrushless);
//        spark7 = new CANSparkMax(7, CANSparkMaxLowLevel.MotorType.kBrushless);

        spark7 = new CANSparkMax(7, MotorType.kBrushless);
        spark8 = new CANSparkMax(8, MotorType.kBrushless);

        lift = new SpeedControllerGroup(spark7,spark8);

        encoder1 = spark7.getEncoder();

        bottomValue = encoder1.getPosition();

    }

    /**
     * This drives teh lift up at the designated speed
     * @param power
     */
    public void liftUp(double power){
        lift.set(power);
    }
    /**
     * This drives teh lift up at the designated speed
     * @param power
     */
    private void liftSet(double power){
        lift.set(power);
    }

    /**
     * Drives the lift down at the specified power
     * @param power
     */
    public void liftDown(double power){
        lift.set(-power);
    }

    /**
     * This method stops the lift
     */
    public void stopLift(){
        lift.set(0);
    }

    /**
     * This method drives the lift up to the specified encoder height
     * @param height
     */
    private void driveUpToEncoder(double height, double power){

        double heightAdjusted = height * cmToEncoderValues;
        //
        while (encoder1.getPosition()<=heightAdjusted){
            liftUp(power);
        }
        stopLift();

    }

    /**
     * This method drives the lift down to the specified height
     * @param height
     */
    private void driveDownToEncoder(double height, double power){

        double heightAdjusted = height * cmToEncoderValues;

        //ToDo: Ramp up/down?
        while (encoder1.getPosition()>=heightAdjusted){
            liftDown(power);
        }
        stopLift();


    }

    public void toEncoderHeight(double height){

        double positionWanted = height + getBottomValue() - encoder1.getPosition();

        //double heightAdjusted = positionWanted * cmToEncoderValues;

        if (positionWanted>=0){
            //driveUpToEncoder(positionWanted,power);
            while (encoder1.getPosition()<=positionWanted){
                liftUp(1);
            }
            stopLift();
        }else{
            //driveDownToEncoder(abs(positionWanted),power);
            while (encoder1.getPosition()>=positionWanted){
                liftDown(-0.4);
            }
            stopLift();
        }
        stopLift();

    }

    /**
     * holds the lift at whatever the current height is
     */
    public void hold(){
        lift.set(HOLD_POWER);
        isHolding = true;
    }

    public void setBottomValue(){
        bottomValue = encoder1.getPosition();
    }

    public double getBottomValue(){
        return bottomValue;
    }

//ToDo: other lift methods?

    @Override
    public void publishStats() {
        SmartDashboard.putNumber(("spark8 encoder position: "), encoder1.getPosition());
        SmartDashboard.putNumber("Spark8 encoder velocity: ", encoder1.getVelocity());
        SmartDashboard.putBoolean("holding",isHolding);
        SmartDashboard.putNumber("Bottom pos",bottomValue);

    }

    @Override
    public void test() {

    }
}
