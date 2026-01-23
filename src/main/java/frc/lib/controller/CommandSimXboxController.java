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

package frc.lib.controller;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.event.EventLoop;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.KController;

public class CommandSimXboxController extends CommandXboxController {
  private final SimXboxController m_hid;
  private final ControllerMapping mapping;

  public CommandSimXboxController(int port) {
    super(port);
    switch (KController.kSimControllerType) {
      case XBOX:
        mapping = ControllerMappings.XBOX_MAPPING;
        m_hid = new SimXboxController(port, mapping);
        break;
      case DUAL_SENSE:
        mapping = ControllerMappings.DUALSENSE_MAPPING;
        m_hid = new SimXboxController(port, mapping);
        break;
      default:
        mapping = ControllerMappings.XBOX_MAPPING;
        m_hid = new SimXboxController(port, mapping);
        break;
    }
  }

  @Override
  public XboxController getHID() {
    return m_hid;
  }

  @Override
  public Trigger a(EventLoop loop) {
    return button(mapping.getButton("A"), loop);
  }

  @Override
  public Trigger b(EventLoop loop) {
    return button(mapping.getButton("B"), loop);
  }

  @Override
  public Trigger x(EventLoop loop) {
    return button(mapping.getButton("X"), loop);
  }

  @Override
  public Trigger y(EventLoop loop) {
    return button(mapping.getButton("Y"), loop);
  }

  @Override
  public Trigger leftBumper(EventLoop loop) {
    return button(mapping.getButton("LeftBumper"), loop);
  }

  @Override
  public Trigger rightBumper(EventLoop loop) {
    return button(mapping.getButton("RightBumper"), loop);
  }

  @Override
  public Trigger back(EventLoop loop) {
    return button(mapping.getButton("Back"), loop);
  }

  @Override
  public Trigger start(EventLoop loop) {
    return button(mapping.getButton("Start"), loop);
  }

  @Override
  public Trigger leftStick(EventLoop loop) {
    return button(mapping.getButton("LeftStick"), loop);
  }

  @Override
  public Trigger rightStick(EventLoop loop) {
    return button(mapping.getButton("RightStick"), loop);
  }

  @Override
  public Trigger leftTrigger(double threshold, EventLoop loop) {
    return axisGreaterThan(mapping.getAxis("LeftTrigger"), threshold, loop);
  }

  @Override
  public Trigger rightTrigger(double threshold, EventLoop loop) {
    return axisGreaterThan(mapping.getAxis("RightTrigger"), threshold, loop);
  }

  @Override
  public double getLeftX() {
    return getRawAxis(mapping.getAxis("LeftX"));
  }

  @Override
  public double getRightX() {
    return getRawAxis(mapping.getAxis("RightX"));
  }

  @Override
  public double getLeftY() {
    return getRawAxis(mapping.getAxis("LeftY"));
  }

  @Override
  public double getRightY() {
    return getRawAxis(mapping.getAxis("RightY"));
  }

  @Override
  public double getLeftTriggerAxis() {
    return m_hid.getLeftTriggerAxis();
  }

  @Override
  public double getRightTriggerAxis() {
    return m_hid.getRightTriggerAxis();
  }
}
