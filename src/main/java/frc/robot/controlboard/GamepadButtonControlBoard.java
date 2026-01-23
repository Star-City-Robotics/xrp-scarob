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
import frc.robot.Constants.KOperator;
import frc.robot.Robot;
import frc.lib.controller.CommandSimXboxController;
import edu.wpi.first.math.filter.Debouncer.DebounceType;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

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
    private final CommandXboxController additionalController;

    @SuppressWarnings("unused")
    private GamepadButtonControlBoard() {
        if (Constants.kForceDriveGamepad
                || DriverStation.getJoystickIsXbox(Constants.kDriveGamepadPort)) {
            if (Robot.isSimulation()) {
                controller = new CommandSimXboxController(Constants.kDriveGamepadPort);
            } else {
                controller = new CommandXboxController(Constants.kDriveGamepadPort);
            }
            additionalController =
                    new CommandXboxController(Constants.kGamepadAdditionalControllerPort);
        } else {
            controller = new CommandXboxController(Constants.kOperatorControllerPort);
        }
    }

    @Override
    public Trigger getWantToXWheels() {
        return controller.start().and(controller.back().negate());
    }

    @Override
    public Trigger getWantToAutoAlign() {
        return controller.start();
    }

    @Override
    public Trigger score() {
        return controller.rightTrigger();
    }

    @Override
    public Trigger scoreBarge() {
        return controller.b();
    }

    @Override
    public Trigger reefIntakeAlgae() {
        return controller.a();
    }

    @Override
    public Trigger bargeManualStage() {
        return controller.y();
    }

    @Override
    public Trigger processorManualStage() {
        return controller.x();
    }

    @Override
    public Trigger stow() {
        return controller.leftStick();
    }

    @Override
    public Trigger intake() {
        return controller.leftTrigger();
    }

    @Override
    public Trigger intakeFunnel() {
        return controller.povLeft();
    }

    @Override
    public Trigger exhaust() {
        return controller.povRight();
    }

    @Override
    public Trigger climb() {
        return controller.rightBumper();
    }

    @Override
    public Trigger stageL1() {
        return controller.x();
    }

    @Override
    public Trigger stageL2() {
        return controller.a();
    }

    @Override
    public Trigger stageL3() {
        return controller.b();
    }

    @Override
    public Trigger stageL4() {
        return controller.y();
    }

    @Override
    public Trigger getCoralMode() {
        return controller.start().and(controller.back().negate()).debounce(0.1);
    }

    @Override
    public Trigger getAlgaeClimbMode() {
        return controller.back().and(controller.start().negate()).debounce(0.1);
    }

    @Override
    public Trigger getCoralManualMode() {
        return controller.back().and(controller.start()).debounce(0.5, DebounceType.kBoth);
    }

    @Override
    public Trigger autoAlignReefIntake() {
        return controller.leftBumper();
    }

    @Override
    public Trigger manualIntakeAlgae() {
        return controller.rightStick();
    }

    @Override
    public Trigger autoAlignLeft() {
        return controller.leftBumper();
    }

    @Override
    public Trigger groundIntakeDeployManual() {
        return controller.povUp();
    }

    @Override
    public Trigger descoreManual() {
        return controller.rightBumper();
    }

    @Override
    public Trigger groundIntakeDeploySpinManual() {
        return controller.povDown();
    }

    @Override
    public Trigger autoAlignRight() {
        return controller.rightBumper();
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
    public Trigger autoAlignFeeder() {
        return controller.rightStick().debounce(Constants.kPOVDebounceTimeSeconds);
    }

    @Override
    public Trigger setDefaultRobotWide() {
        return controller.povDown();
    }

    @Override
    public Trigger setDefaultRobotTight() {
        return controller.povUp();
    }

    @Override
    public Trigger lollipopIntake() {
        return controller.rightBumper();
    }

    @Override
    public void setRumble(boolean rumble) {
        controller.getHID().setRumble(RumbleType.kBothRumble, rumble ? 1 : 0);
    }
}
