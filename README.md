# BreadLog  
Logging framework developed by Team 5940 BREAD

BreadLog is a lightweight logging library designed for FRC teams. It provides a simple way to record robot states, sensor data, subsystem values, and poses during both real and simulation operations.

BreadLog was originally built for 5940, but is designed to be general enough for any team to integrate and modify.

---

## Features

- High-speed logging
- Simple logging for numbers, booleans, strings, and structured pose data
- Simple start with one line in `Robot.java`
- Compatible with NetworkTables dashboards (Glass, AdvantageScope, etc.)

---

## Quick Start

### Installation
Place `BreadLog.java` under `src\main\java\frc\util`

### Import
At the top of `Robot.java`, place `import frc.util.BreadLog;`

### Starting the logger
In `RobotInit()`, place `BreadLog.start();` putting `true` or `false` in as a parameter for whether or not it should log the output or not, respectively

### Start logging!!

---

## Docs and Usage

### Starting/Stopping Logger
To start the logger with logging to a file, use `BreadLog.start(true);`
To start the logger without logging to a file, use `BreadLog.start(false);`
