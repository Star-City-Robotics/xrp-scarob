// MIT License
//
// Copyright (c) 2025 Team 254
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

package frc.robot.controlboard;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.lib.controller.CommandSimXboxController;
// import edu.wpi.first.math.filter.Debouncer.DebounceType;
import frc.robot.Constants.KOperator;
import frc.robot.Robot;

public class GamepadButtonControlBoard implements IButtonControlBoard {
  private static GamepadButtonControlBoard instance = null;

  public static GamepadButtonControlBoard getInstance() {
    if (instance == null) {
      instance = new GamepadButtonControlBoard();
    }
    return instance;
  }

  private final CommandXboxController controller;

  @SuppressWarnings("unused")
  private GamepadButtonControlBoard() {
    if (KOperator.kForceDriveGamepad
        || DriverStation.getJoystickIsXbox(KOperator.kDriverControllerPort)) {
      if (Robot.isSimulation()) {
        controller = new CommandSimXboxController(KOperator.kDriverControllerPort);
      } else {
        controller = new CommandXboxController(KOperator.kDriverControllerPort);
      }
    } else {
      controller = new CommandXboxController(KOperator.kOperatorControllerPort);
    }
  }

  @Override
  public Trigger leftStick() {
    return controller.leftStick();
  }

  @Override
  public Trigger rightStick() {
    return controller.rightStick();
  }

  @Override
  public Trigger leftBumper() {
    return controller.leftBumper();
  }

  @Override
  public Trigger rightBumper() {
    return controller.rightBumper();
  }

  @Override
  public Trigger leftTrigger() {
    return controller.leftBumper();
  }

  @Override
  public Trigger rightTrigger() {
    return controller.rightBumper();
  }

  @Override
  public Trigger a() {
    return controller.a();
  }

  @Override
  public Trigger b() {
    return controller.b();
  }

  @Override
  public Trigger x() {
    return controller.x();
  }

  @Override
  public Trigger y() {
    return controller.y();
  }

  @Override
  public Trigger povUp() {
    return controller.povUp();
  }

  @Override
  public Trigger povDown() {
    return controller.povDown();
  }

  @Override
  public Trigger povLeft() {
    return controller.povLeft();
  }

  @Override
  public Trigger povRight() {
    return controller.povRight();
  }

  @Override
  public Trigger back() {
    return controller.back();
  }

  @Override
  public Trigger start() {
    return controller.start();
  }

  @Override
  public void setRumble(boolean rumble) {
    controller.getHID().setRumble(RumbleType.kBothRumble, rumble ? 1 : 0);
  }
}
