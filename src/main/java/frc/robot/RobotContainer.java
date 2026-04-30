// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.epilogue.NotLogged;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Indexer;

public class RobotContainer {

  @NotLogged private final XboxController controller;
  private final Indexer indexer;

  public RobotContainer() {
    controller = new XboxController(0);

    indexer = new Indexer();
    

    configureBindings();
  }

  private void configureBindings() {
    Trigger indexerForward = new Trigger(() -> controller.getLeftTriggerAxis() > 0.15 && !indexer.ballIsSensed());
    indexerForward.onTrue(indexer.setSpeedCommand(1));
    indexerForward.onFalse(indexer.setSpeedCommand(0));

    Trigger indexerReverse = new Trigger(() -> controller.getXButton());
    indexerReverse.onTrue(indexer.setSpeedCommand(-1));
    indexerReverse.onFalse(indexer.setSpeedCommand(0));

  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
