package frc.util;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.networktables.BooleanPublisher;
import edu.wpi.first.networktables.DoublePublisher;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.StringPublisher;
import edu.wpi.first.networktables.StructPublisher;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.Timer;

public class BreadLog {

    private static final String PREFIX = "/BreadLog/";
    private static final NetworkTableInstance inst = NetworkTableInstance.getDefault();

    private static final Map<String, DoublePublisher> numberPubs = new HashMap<>();
    private static final Map<String, BooleanPublisher> boolPubs = new HashMap<>();
    private static final Map<String, StringPublisher> stringPubs = new HashMap<>();

    private static final Map<String, StructPublisher<Pose2d>> pose2dStructPubs = new HashMap<>();
    private static final Map<String, StructPublisher<Pose3d>> pose3dStructPubs = new HashMap<>();
    private static final Map<String, StructPublisher<Translation2d>> translation2dStructPubs = new HashMap<>();
    private static final Map<String, StructPublisher<Translation3d>> translation3dStructPubs = new HashMap<>();
    private static final Map<String, StructPublisher<Rotation2d>> rotation2dStructPubs = new HashMap<>();
    private static final Map<String, StructPublisher<Rotation3d>> rotation3dStructPubs = new HashMap<>();

    private static boolean started = false;

    private static double frameStartTime = 0.0;

    public static void startFrame() {
        frameStartTime = Timer.getFPGATimestamp();
    }

    public static void endFrame() {
        inst.flush();
    }

    public static double getFrameTime() {
        return Timer.getFPGATimestamp() - frameStartTime;
    }

    private static String frameTimestamped(String msg) {
        long us = (long) (getFrameTime() * 1_000_000);
        return "[" + us + "] " + msg;
    }

    public static void start(boolean recordToFile) {
        if (started) return;
        started = true;

        inst.startServer();

        if (recordToFile) {
            try {
                java.nio.file.Files.createDirectories(java.nio.file.Paths.get("logs"));
            } catch (Exception e) {
                System.out.println("BreadLog: Failed to create log folder!");
            }
            DataLogManager.start("logs/");
            DataLogManager.logNetworkTables(true);
        }

        logEvent("BreadLogStarted");
    }

    public static void stop() {
        if (!started) return;
        logEvent("BreadLogStopped");
        inst.flush();
        started = false;
    }

    public static void logNumber(String key, double value) {
        DoublePublisher pub = numberPubs.computeIfAbsent(key,
                k -> inst.getDoubleTopic(PREFIX + k).publish());
        pub.set(value);
    }

    public static void logBoolean(String key, boolean value) {
        BooleanPublisher pub = boolPubs.computeIfAbsent(key,
                k -> inst.getBooleanTopic(PREFIX + k).publish());
        pub.set(value);
    }

    public static void logString(String key, String value) {
        StringPublisher pub = stringPubs.computeIfAbsent(key,
                k -> inst.getStringTopic(PREFIX + k).publish());
        pub.set(value);
    }

    public static void logEvent(String message) {
        logString("Events", frameTimestamped(message));
    }

    public static void warn(String message) {
        logString("Warnings", frameTimestamped(message));
    }

    public static void error(String message) {
        logString("Errors", frameTimestamped(message));
    }

    private static StructPublisher<Pose2d> getPose2dStructPublisher(String key) {
        return pose2dStructPubs.computeIfAbsent(key,
                k -> inst.getStructTopic(PREFIX + k, Pose2d.struct).publish());
    }

    private static StructPublisher<Pose3d> getPose3dStructPublisher(String key) {
        return pose3dStructPubs.computeIfAbsent(key,
                k -> inst.getStructTopic(PREFIX + k, Pose3d.struct).publish());
    }

    private static StructPublisher<Translation2d> getTranslation2dStructPublisher(String key) {
        return translation2dStructPubs.computeIfAbsent(key,
                k -> inst.getStructTopic(PREFIX + k, Translation2d.struct).publish());
    }

    private static StructPublisher<Translation3d> getTranslation3dStructPublisher(String key) {
        return translation3dStructPubs.computeIfAbsent(key,
                k -> inst.getStructTopic(PREFIX + k, Translation3d.struct).publish());
    }

    private static StructPublisher<Rotation2d> getRotation2dStructPublisher(String key) {
        return rotation2dStructPubs.computeIfAbsent(key,
                k -> inst.getStructTopic(PREFIX + k, Rotation2d.struct).publish());
    }

    private static StructPublisher<Rotation3d> getRotation3dStructPublisher(String key) {
        return rotation3dStructPubs.computeIfAbsent(key,
                k -> inst.getStructTopic(PREFIX + k, Rotation3d.struct).publish());
    }

    public static void logPose2d(String key, Pose2d pose) {
        getPose2dStructPublisher(key).set(pose);
    }

    public static void logPose3d(String key, Pose3d pose) {
        getPose3dStructPublisher(key).set(pose);
    }

    public static void logTranslation2d(String key, Translation2d t) {
        getTranslation2dStructPublisher(key).set(t);
    }

    public static void logTranslation3d(String key, Translation3d t) {
        getTranslation3dStructPublisher(key).set(t);
    }

    public static void logRotation2d(String key, Rotation2d r) {
        getRotation2dStructPublisher(key).set(r);
    }

    public static void logRotation3d(String key, Rotation3d r) {
        getRotation3dStructPublisher(key).set(r);
    }
}
