package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.cameraserver.CameraServer;
import org.opencv.video.Video;

public class Cameras implements SubSystem {

    private UsbCamera camera1;
    private UsbCamera camera2;
    private VideoSink server;

    public Cameras(){
        //we assume this is cargo
        camera1 = CameraServer.getInstance().startAutomaticCapture(0);
        camera1.setFPS(15);
        //we assume this is hatch
        camera2 = CameraServer.getInstance().startAutomaticCapture(1);
        camera2.setFPS(15);
/*
        server = CameraServer.getInstance().getServer();
        camera1.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen);
        camera2.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen);
*/
    }


    public void setCargoToFront(){
  //      server.setSource(camera1);

    }

    public void setHatchToFront(){
   //     server.setSource(camera2);
    }





    @Override
    public void publishStats() {

    }

    @Override
    public void test() {

    }
}
