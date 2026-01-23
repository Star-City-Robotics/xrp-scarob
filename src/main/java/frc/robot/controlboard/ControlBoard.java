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

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class ControlBoard implements IDriveControlBoard, IButtonControlBoard {
  private static ControlBoard instance = null;

  public static ControlBoard getInstance() {
    if (instance == null) {
      instance = new ControlBoard();
    }
    return instance;
  }

  private final IDriveControlBoard driveControlBoard;
  private final IButtonControlBoard buttonControlBoard;

  private ControlBoard() {
    driveControlBoard = GamepadDriveControlBoard.getInstance();
    buttonControlBoard = GamepadButtonControlBoard.getInstance();
  }

  // =============================================
  // Driver

  // ---------------------------------------------
  // Driver Gamepad: right stick

  @Override
  public double getThrottle() {
    return driveControlBoard.getThrottle();
  }

  @Override
  public double getStrafe() {
    return driveControlBoard.getStrafe();
  }

  // ---------------------------------------------
  // Driver Gamepad: right stick

  @Override
  public double getRotation() {
    return driveControlBoard.getRotation();
  }

  @Override
  public double getRotationY() {
    return driveControlBoard.getRotationY();
  }

  // ---------------------------------------------
  // Driver Gamepad: reset gyro

  @Override
  public Trigger resetGyro() {
    return driveControlBoard.resetGyro();
  }

  // =============================================
  // Operator

  // ---------------------------------------------
  // Operator Gamepad: Joysticks
  @Override
  public Trigger leftStick() {
    return buttonControlBoard.leftStick();
  }

  @Override
  public Trigger rightStick() {
    return buttonControlBoard.rightStick();
  }

  // ---------------------------------------------
  // Operator Gamepad: Bumpers
  @Override
  public Trigger leftBumper() {
    return buttonControlBoard.leftBumper();
  }

  @Override
  public Trigger rightBumper() {
    return buttonControlBoard.rightBumper();
  }

  // ---------------------------------------------
  // Operator Gamepad: Triggers
  @Override
  public Trigger leftTrigger() {
    return buttonControlBoard.leftTrigger();
  }

  @Override
  public Trigger rightTrigger() {
    return buttonControlBoard.rightTrigger();
  }

  // ---------------------------------------------
  // Operator Gamepad: a b x y
  @Override
  public Trigger a() {
    return buttonControlBoard.a();
  }

  @Override
  public Trigger b() {
    return buttonControlBoard.b();
  }

  @Override
  public Trigger x() {
    return buttonControlBoard.x();
  }

  @Override
  public Trigger y() {
    return buttonControlBoard.y();
  }

  // ---------------------------------------------
  // Operator Gamepad: povUp povDown povLeft povRight
  @Override
  public Trigger povUp() {
    return buttonControlBoard.povUp();
  }

  @Override
  public Trigger povDown() {
    return buttonControlBoard.povDown();
  }

  @Override
  public Trigger povLeft() {
    return buttonControlBoard.povLeft();
  }

  @Override
  public Trigger povRight() {
    return buttonControlBoard.povRight();
  }

  // ---------------------------------------------
  // Operator Gamepad: start back
  @Override
  public Trigger start() {
    return buttonControlBoard.start();
  }

  @Override
  public Trigger back() {
    return buttonControlBoard.back();
  }

  // ---------------------------------------------
  // Operator Gamepad: rumble
  @Override
  public void setRumble(boolean rumble) {
    buttonControlBoard.setRumble(rumble);
  }

  public Command rumble() {
    return Commands.startEnd(() -> setRumble(true), () -> setRumble(false));
  }
}
