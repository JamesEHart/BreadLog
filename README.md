# BreadLog  
A logging framework made by [Finn](https://github.com/JamesEHart) for Team 5940 BREAD

BreadLog is a lightweight logging library designed for FRC teams. It provides a simple way to record robot states, sensor data, subsystem values, and poses during both real and simulation operations.

BreadLog was originally built for 5940, but is designed to be general enough for any team to integrate and modify.

---

## Features

- High-speed logging via NetworkTables
- Log numbers, booleans, strings, events, warnings, errors, and geometry types
- Simple setup with one line in `Robot.java`
- Optional file logging with automatic log folder creation
- Frame timing utilities for loop performance tracking
- Compatible with NetworkTables dashboards (Glass, AdvantageScope, etc.)

---

## Quick Start

### Installation
Place `BreadLog.java` under `src/main/java/frc/util/`

### Import
```java
import frc.util.BreadLog;
```

### Setup in `robotInit()`
```java
BreadLog.start(true);  // true = save logs to file, false = NetworkTables only
```

### Frame timing (optional, in `robotPeriodic()`)
```java
BreadLog.startFrame();
// ... your periodic code ...
BreadLog.endFrame();
```

---

## API Reference

### Lifecycle

| Method | Description |
|---|---|
| `BreadLog.start(boolean recordToFile)` | Starts the logger. Pass `true` to save logs to the `logs/` folder, `false` for NetworkTables only. |
| `BreadLog.stop()` | Flushes and stops the logger. |
| `BreadLog.startFrame()` | Records the start time of the current loop frame. |
| `BreadLog.endFrame()` | Flushes all pending NetworkTables updates. |
| `BreadLog.getFrameTime()` | Returns elapsed time in seconds since `startFrame()` was called. |

---

### Logging Primitives

All values are published to NetworkTables under the `/BreadLog/<key>` topic.

| Method | Description |
|---|---|
| `BreadLog.logNumber(String key, double value)` | Log a numeric value (doubles, ints, etc.). |
| `BreadLog.logBoolean(String key, boolean value)` | Log a boolean value. |
| `BreadLog.logString(String key, String value)` | Log a string value. |

**Example:**
```java
BreadLog.logNumber("Drive/LeftSpeed", leftMotor.get());
BreadLog.logBoolean("Intake/HasNote", intakeSensor.get());
BreadLog.logString("Robot/State", currentState.name());
```

---

### Events, Warnings, and Errors

These log timestamped messages (relative to the current frame) to fixed keys.

| Method | Published to | Description |
|---|---|---|
| `BreadLog.logEvent(String message)` | `/BreadLog/Events` | Log a general event. |
| `BreadLog.warn(String message)` | `/BreadLog/Warnings` | Log a warning. |
| `BreadLog.error(String message)` | `/BreadLog/Errors` | Log an error. |

**Example:**
```java
BreadLog.logEvent("AutoStarted");
BreadLog.warn("Vision target lost");
BreadLog.error("Motor controller disconnected");
```

---

### Geometry Logging

Geometry types are published as WPILib structs, making them compatible with AdvantageScope's 2D/3D field views.

| Method | Type |
|---|---|
| `BreadLog.logPose2d(String key, Pose2d pose)` | `Pose2d` |
| `BreadLog.logPose3d(String key, Pose3d pose)` | `Pose3d` |
| `BreadLog.logTranslation2d(String key, Translation2d t)` | `Translation2d` |
| `BreadLog.logTranslation3d(String key, Translation3d t)` | `Translation3d` |
| `BreadLog.logRotation2d(String key, Rotation2d r)` | `Rotation2d` |
| `BreadLog.logRotation3d(String key, Rotation3d r)` | `Rotation3d` |

**Example:**
```java
BreadLog.logPose2d("Drive/EstimatedPose", poseEstimator.getEstimatedPosition());
BreadLog.logRotation2d("Drive/Heading", gyro.getRotation2d());
```
