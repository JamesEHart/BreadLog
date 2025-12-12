# BreadLog  
Logging framework developed by FRC Team 5940 Bread

BreadLog is a lightweight, extensible, and robotics-focused logging library designed for FIRST Robotics Competition teams. It provides a simple API for recording robot state, sensor data, subsystem values, and poses during both real-robot operation and simulation.

BreadLog was originally built for FRC Team 5940 Bread, but is designed to be general enough for any team to integrate.

---

## Features

- High-speed, low-overhead logging suitable for 20ms robot loops  
- Simple API for numbers, booleans, strings, and structured pose data  
- Full support for Pose2d and Pose3d logging  
- WPILib-friendly design for subsystem and command frameworks  
- Automatic update cycle with a single call in `robotPeriodic()`  
- Compatible with NetworkTables dashboards (Glass, AdvantageScope, etc.)  
- Optional logging for simulation environments  

---

## Basic Usage

### Logging Numbers
