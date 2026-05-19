package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.RPM;
import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.epilogue.NotLogged;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Velocity;
import edu.wpi.first.units.measure.Voltage;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;

@Logged
public class Shooter extends SubsystemBase {
    
    private final SparkMax motor;
    private final SparkMaxConfig config;
    @NotLogged private SparkClosedLoopController motorController;
    private Voltage voltage;
    private AngularVelocity velocity;
    private Current current;
    private AngularVelocity setpoint;

    public Shooter() {
        motor = new SparkMax(ShooterConstants.MOTOR_ID, MotorType.kBrushless);
        config = new SparkMaxConfig();
        config.smartCurrentLimit((int) ShooterConstants.CURRENT_LIMIT.in(Amps));
        motor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        motorController = motor.getClosedLoopController();

        voltage = Volts.of(0);
        velocity = RPM.of(0);
        current = Amps.of(0);
        setpoint = RPM.of(0);
    }

    @Override
    public void periodic() {
        voltage = Volts.of(motor.getAppliedOutput() * motor.getBusVoltage());
        velocity = RPM.of(motor.getEncoder().getVelocity());
        current = Amps.of(motor.getOutputCurrent());
    }

    @Override
    public void simulationPeriodic() {
        velocity = setpoint;
    }

    private void shoot(AngularVelocity velocity) {
        motorController.setSetpoint(velocity.in(RPM), ControlType.kVelocity);
    }

    public Command setVelocityCommand(
        AngularVelocity velocity) {
        return Commands.runOnce(() -> shoot(velocity), this);
    }
}
