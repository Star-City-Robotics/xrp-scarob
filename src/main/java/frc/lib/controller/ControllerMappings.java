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

import java.util.HashMap;
import java.util.Map;

public class ControllerMappings {
  public static final ControllerMapping XBOX_MAPPING;
  public static final ControllerMapping DUALSENSE_MAPPING;
  // public static final ControllerMapping KEYBOARD_MAPPING;

  static {
    Map<String, Integer> xboxButtons = new HashMap<>();
    xboxButtons.put("A", 1);
    xboxButtons.put("B", 2);
    xboxButtons.put("X", 4);
    xboxButtons.put("Y", 5);
    xboxButtons.put("LeftBumper", 7);
    xboxButtons.put("RightBumper", 8);
    xboxButtons.put("Back", 11);
    xboxButtons.put("Start", 12);
    xboxButtons.put("LeftStick", 14);
    xboxButtons.put("RightStick", 15);

    Map<String, Integer> xboxAxes = new HashMap<>();
    xboxAxes.put("LeftX", 0);
    xboxAxes.put("LeftY", 1);
    xboxAxes.put("RightX", 3);
    xboxAxes.put("RightY", 4);
    xboxAxes.put("RightTrigger", 2);
    xboxAxes.put("LeftTrigger", 5);

    XBOX_MAPPING = new ControllerMapping(xboxButtons, xboxAxes);

    Map<String, Integer> dualSenseButtons = new HashMap<>();
    dualSenseButtons.put("A", 1);
    dualSenseButtons.put("B", 2);
    dualSenseButtons.put("X", 3);
    dualSenseButtons.put("Y", 4);
    dualSenseButtons.put("LeftBumper", 5);
    dualSenseButtons.put("RightBumper", 6);
    dualSenseButtons.put("Back", 7);
    dualSenseButtons.put("Start", 8);
    dualSenseButtons.put("LeftStick", 10);
    dualSenseButtons.put("RightStick", 11);

    // NOTE: untested, may need to be adjusted
    Map<String, Integer> dualSenseAxes = new HashMap<>();
    dualSenseAxes.put("LeftX", 0);
    dualSenseAxes.put("LeftY", 1);
    dualSenseAxes.put("RightX", 4);
    dualSenseAxes.put("RightY", 5);
    dualSenseAxes.put("LeftTrigger", 2);
    dualSenseAxes.put("RightTrigger", 3);

    DUALSENSE_MAPPING = new ControllerMapping(dualSenseButtons, dualSenseAxes);

    // Map<String, Integer> keyboardButtons = new HashMap<>();
    // keyboardButtons.put("A", 1);
    // keyboardButtons.put("B", 2);
    // keyboardButtons.put("X", 4);
    // keyboardButtons.put("Y", 5);
    // keyboardButtons.put("LeftBumper", 7);
    // keyboardButtons.put("RightBumper", 8);
    // keyboardButtons.put("Back", 11);
    // keyboardButtons.put("Start", 12);
    // keyboardButtons.put("LeftStick", 14);
    // keyboardButtons.put("RightStick", 15);

    // Map<String, Integer> keyboardAxes = new HashMap<>();
    // keyboardAxes.put("LeftX", 0);
    // keyboardAxes.put("LeftY", 1);
    // keyboardAxes.put("RightX", 2);
    // keyboardAxes.put("RightY", 3);
    // keyboardAxes.put("RightTrigger", 4);
    // keyboardAxes.put("LeftTrigger", 5);

    // KEYBOARD_MAPPING = new ControllerMapping(keyboardButtons, keyboardAxes);
  }
}
