// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.RPM;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;

public final class Constants {
    public static class ShooterConstants {
        public static final int MOTOR_ID = 10;
        
        public static final AngularVelocity SHOOTER_SPEED = RPM.of(4800);
        public static final AngularVelocity IDLE_SPEED = RPM.of(1500);

        public static final Current CURRENT_LIMIT = Amps.of(80);
    }
}
