package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.RPM;
import static edu.wpi.first.units.Units.Volts;

import com.revrobotics.spark.SparkMax;

import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.epilogue.NotLogged;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IndexerConstants;
import com.revrobotics.spark.ResetMode;
import com.revrobotics.spark.PersistMode;
import edu.wpi.first.wpilibj.DigitalInput;



public class Indexer extends SubsystemBase {

    @NotLogged private final SparkMax motor;
    @NotLogged private final SparkMaxConfig config;

    private Voltage voltage;
    private AngularVelocity velocity;
    private Current current;

    private double setpoint;

    private final DigitalInput distanceSensor;

    public Indexer(){
        motor = new SparkMax(IndexerConstants.MOTOR_PORT, MotorType.kBrushless);

        config = new SparkMaxConfig();
        config.smartCurrentLimit((int) IndexerConstants.CURRENT_LIMIT.in(Amps));
        config.idleMode(IdleMode.kBrake);
        motor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        distanceSensor = new DigitalInput(ClimberConstants.DISTANCE_SWITCH_PORT);
    }

       public void periodic(){
       current = Amps.of(motor.getOutputCurrent());
       voltage = Volts.of(motor.getAppliedOutput() * motor.getBusVoltage());
       velocity = RPM.of(motor.getEncoder().getVelocity());
   }

   public void simulationPeriodic() {
       velocity = setpoint;
   }

   private void setSpeed(double speed) {
       motor.set(speed);
   }

   public Command setSpeedCommand(double speed){
       return Commands.runOnce(() -> setSpeed(speed), this);
   }

   public Boolean ballIsSensed() {
    return distanceSensor.get() == 1;
   }
   
}
