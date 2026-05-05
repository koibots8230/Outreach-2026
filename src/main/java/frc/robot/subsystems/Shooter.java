package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.RPM;
import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.epilogue.NotLogged;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

@Logged
public class Shooter extends SubsystemBase {
    
    private final Spark motor;
    private final SparkMaxConfig motorConfig;

    public Shooter() {
        motor = new SparkMax(ShooterConstants.MOTOR_ID, MotorType.kBrushless);
        motorConfig = new SparkMaxConfig();
        motorConfig.idleMode(IdleMode.kCoast);

        motor.configure(
            motorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
}
