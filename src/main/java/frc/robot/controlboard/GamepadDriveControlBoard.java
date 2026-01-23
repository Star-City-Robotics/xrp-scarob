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

import frc.robot.Constants;
import frc.robot.Robot;
import frc.lib.controller.CommandSimXboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class GamepadDriveControlBoard implements IDriveControlBoard {
    private static GamepadDriveControlBoard instance = null;

    public static GamepadDriveControlBoard getInstance() {
        if (instance == null) {
            instance = new GamepadDriveControlBoard();
        }

        return instance;
    }

    private final CommandXboxController controller;

    private GamepadDriveControlBoard() {
        if (Robot.isSimulation()) {
            controller = new CommandSimXboxController(Constants.kDriveGamepadPort);
        } else {
            controller = new CommandXboxController(Constants.kDriveGamepadPort);
        }
    }

    @Override
    public double getThrottle() {
        return -(Math.pow(Math.abs(controller.getLeftY()), 1.5))
                * Math.signum(controller.getLeftY());
    }

    @Override
    public double getStrafe() {
        return -(Math.pow(Math.abs(controller.getLeftX()), 1.5))
                * Math.signum(controller.getLeftX());
    }

    @Override
    public double getRotation() {
        return -(Math.pow(Math.abs(controller.getRightX()), 2.0))
                * Math.signum(controller.getRightX());
    }

    @Override
    public double getRotationY() {
        return -(Math.pow(Math.abs(controller.getRightY()), 2.0))
                * Math.signum(controller.getRightY());
    }

    @Override
    public Trigger resetGyro() {
        return controller.back().and(controller.start().negate());
    }
}
