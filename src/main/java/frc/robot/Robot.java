package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import frc.util.BreadLog;

public class Robot extends TimedRobot {

    @Override
    public void robotInit() {
  BreadLog.start(true);
    }
  
    @Override
    public void robotPeriodic() {
    }
  
    @Override
    public void disabledInit() {
    }
  
    @Override
    public void disabledPeriodic() {
    }
  
    @Override
    public void autonomousInit() {
    }
  
    @Override
    public void teleopPeriodic() {
        BreadLog.logNumber("Time", Timer.getFPGATimestamp());
        BreadLog.logPose2d("Pose2dTest", new Pose2d(new Translation2d(10, 5), new Rotation2d(Math.toRadians(45))));
    }
  
    @Override
    public void autonomousPeriodic() {
    }
  
    @Override
    public void teleopInit() {
    }
  
    @Override
    public void testInit() {
    }
  
    @Override
    public void testPeriodic() {
    }
  
    @Override
    public void simulationInit() {
    }
  
    @Override
    public void simulationPeriodic() {
    }
  }
