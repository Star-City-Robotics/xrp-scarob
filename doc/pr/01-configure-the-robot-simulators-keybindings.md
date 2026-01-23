# Configure the Robot Simulator's Keybindings

[PR 01](https://github.com/Star-City-Robotics/xrp-scarob/pull/1)

## Docs

+ [Robot Simulator](https://docs.wpilib.org/en/stable/docs/software/wpilib-tools/robot-simulation/index.html)
  + [Using The GUI](https://docs.wpilib.org/en/stable/docs/software/wpilib-tools/robot-simulation/simulation-gui.html#using-the-gui) explains what each table and UI element are
  + [Using The Keyboard As A Joystick](https://docs.wpilib.org/en/stable/docs/software/wpilib-tools/robot-simulation/simulation-gui.html#using-the-keyboard-as-a-joystick)

+ [Keyboard Bindings](img/FRC-RobotSimulator-Controls.pdf) (set in this project's simgui-ds.json)

## Launching Robot Simulator

### Setup a controller

Run the project and open Robot Simulator. Find System Joysticks. If your
controller is connected, it should display in the list. Drag either `Generic
X-Box pad` or `Keyboard 0` to `Joystick[0]`.

Some notes on controllers:

> X-Box controllers are easier to program with. We also use them on the team. I
> would recommend the wired "Power A" controller from Walmart, since it's cheap.
> The team may have controllers you could borrow
>
> Wired controllers are simpler to configure quickly. PS4 and PS5 controllers have
> slightly different button bindings, but they still work.
>
> When using the keyboard, the Robot Simulator window requires focus, so usually
> you'll need to click to AdvantageScope, open the 2D or 3D field, then click back
> to Robot Simulator in order to control the robot.

### Customizing Keys

Run the project and open Robot Simulator. Find System Joysticks and right-click `Keyboard 0` to open settings. If you want to restore the keyboard defaults, then just swap `simgui-ds.defaults.json` with `simgui-ds.json` while Robot Simulator is closed.
