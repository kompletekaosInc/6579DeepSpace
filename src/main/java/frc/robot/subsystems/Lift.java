package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

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

    // we need a worker to drive the lift to presets
    private LiftWorker liftWorker;


    public Lift(){
        //Todo: Does the lift own the cameras? y/n

//        spark8 = new CANSparkMax(8, CANSparkMaxLowLevel.MotorType.kBrushless);
//        spark7 = new CANSparkMax(7, CANSparkMaxLowLevel.MotorType.kBrushless);

        spark7 = new CANSparkMax(7, MotorType.kBrushless);
        spark8 = new CANSparkMax(8, MotorType.kBrushless);

        lift = new SpeedControllerGroup(spark7,spark8);

        encoder1 = spark7.getEncoder();

        bottomValue = encoder1.getPosition();

        System.out.println("Creating LiftWorker Thread to drive the presets in the background");
        liftWorker = new LiftWorker();

        System.out.println("start LiftWorker Thread");
        liftWorker.start(); // get the worker to run, awaiting

        System.out.println("Lift ready");
    }

    /**
     * This drives teh lift up at the designated speed
     * @param power
     */
    public void liftUp(double power){

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
     * Drives the lift to a specific height, based on the encoder readings
     * @param height
     */
    public void toEncoderHeight(double height){

        double presetPosition = height + getBottomValue();

        // get the lift worker thread to drive the lift so we can give control back to the robot drivers
        liftWorker.driveLiftToPosition(presetPosition);
    }

    /**
     * drive the lift down to the base position.
     */
    public void toEncoderBase(){

        // get the lift worker thread to drive the lift so we can give control back to the robot drivers
        liftWorker.driveLiftToPosition( getBottomValue() );

//        while (encoder1.getPosition()>positionWanted){
//            liftDown(0.4);
//        }
//        stopLift();
    }

    /**
     * holds the lift at whatever the current height is
     */
    public void hold(){
        // only set hold if the Lift work is not driving the lift to a preset
        if (!liftWorker.isDriving()) {
            lift.set(HOLD_POWER);
            isHolding = true;
        }
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


    /**
     * inner class to create a worker thread to drive the lift (in the background)
     */
    private class LiftWorker extends Thread{

        public LiftWorker() {
            super();
            System.out.println("Constructor for LiftWorker");
        }

        public boolean isDriving() {
            return isDriving;
        }

        //this flag indicates if the worker should drive
        private volatile boolean isDriving = false;  // default we have nothing to do
        private volatile double positionWanted;



        /**
         * This method allows the lift class to delegate driving the lift to a preset position to a background worker thread.
         *
         * @param targetPosition
         */
        public void driveLiftToPosition( double targetPosition ){

            positionWanted = targetPosition;

            // worker thread "run" while loop should detect that we have work to do! drive the lift to the target position
            isDriving = true;
        }

        /**
         * helper to validate that the time taken is less than 5 seconds.
         *
         * @param startTime
         * @return
         */
        private boolean isLessThanLimit( long startTime )
        {
            long msTimeTaken = System.currentTimeMillis() - startTime;
            boolean withInLimit = msTimeTaken < 5000;  // 5 second limit

            if (!withInLimit) {
                System.out.println("The lift toEncoderPosition has taking to long [" + msTimeTaken + "ms]");
            }

            return withInLimit;
        }

        /**
         * determines if the target position is up or down based on the current position of the lift.  This method also has
         * an error handler to stop if the target is not reached within 5 seconds.
         */
        private void toEncoderPosition()
        {
            //System.out.println("LiftWorker.toEncoderPosition:positionWanted="+positionWanted);
            long startTime = System.currentTimeMillis();

            //determine if we are going up or down
            if (encoder1.getPosition()<positionWanted){
                //driveUpToEncoder(positionWanted,power);
                while ((isLessThanLimit(startTime)) && (encoder1.getPosition()<positionWanted)){
                    liftUp(1);
                }
                stopLift();
            }
            else{
                //driveDownToEncoder(abs(positionWanted),power);
                while ((isLessThanLimit(startTime)) && (encoder1.getPosition()>positionWanted)){
                    liftDown(0.4);
                }
                stopLift();
            }
            stopLift();

            // our work is done
            isDriving = false;
        }

        /**
         * This runs the thread for this lift worker, it will start a loop and check every 25ms if there is new work to
         * drive the lift to a preset height.
         */
        @Override
        public void run() {

            // waiting for work
            while (!Thread.interrupted()) {

                //System.out.println("LiftWorker.isDriving:" + isDriving);
                if (isDriving)
                {
                    // we have work to do, drive lift to target height
                    toEncoderPosition();
                }

                // back of to allow other things to happen (200ms sounds right)
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }

        }
    }

}
