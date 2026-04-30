package frc.robot;

import static edu.wpi.first.units.Units.*;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.units.measure.AngularAcceleration;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Time;
import frc.lib.util.FeedforwardGains;
import frc.lib.util.PIDGains;

public class Constants {
    public static class RobotConstants{
        public static final Time ROBOT_CLOCK_SPEED = Time.ofBaseUnits(0.02, Seconds);
    }

    public static class IndexerConstants{
        public static final Current CURRENT_LIMIT = Amps.of(60);

        public static final int DISTANCE_SWITCH_PORT = 9;
        public static final int MOTOR_PORT = 20;
    }
}
