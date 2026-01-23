# Using Singletons to Access The Controlboard

[PR 08](https://github.com/Star-City-Robotics/xrp-scarob/pull/8)

## Resources

+ [Accessing `ControlBoard` in
`RobotContainer`](https://github.com/Team254/FRC-2025-Public/blob/ae1aa582b1cadb8462e6cb90718880d11a8f42b1/src/main/java/com/team254/frc2025/RobotContainer.java#L219-L220), Team254
+ [Delegating access to `Trigger`'s on `ControlBoard` in
`ModalControls`](https://github.com/Team254/FRC-2025-Public/blob/ae1aa582b1cadb8462e6cb90718880d11a8f42b1/src/main/java/com/team254/frc2025/controlboard/ModalControls.java), Team254 (Advanced)

## Team 254 Accesses Their ControlBoard Using Singletons

Team 254 uses "singleton" objects when accessing the `ControlBoard`'s set of triggers. Actually, it's a bit more complicated than that, but for now, let's pretend it's not.

- Each instance of `ControlBoard`, `GamepadDriveControlBoard` and `GamepadButtonControlBoard` separately have a static `getInstance()` method to manage a singleton instance of each.
- This is not a method enforced by the interfaces -- hence the "separately" that's mentioned above. In their robot code, only the `ControlBoard.getInstance()` method seems to be used.

## Benefits of the Singleton Pattern

> While you're reading this, think of all the kinds of objects in WPILib where
> this would be useful: the kinds of objects where only one instance exists.
> Motors, sensors, devices: they should only be created during initialization.

This **singleton pattern** has a few benefits:

1. Helps us access an object which needs to be the **only** instance of that
object **during the lifetime of the application**. i.e. during a single run of the physical robot or the simulated robot
2. "Accelerates" the instantiation of objects. This **almost** happens at the **compilation** phase. I'm not exactly sure when. But by the time our application has started, then these sington objects have been created *and ALL of our classes can ALL access the same objects.*
3. Prevents these objects from being considered for garbage collection. However, some of the references bound to this object will need to be garbage collected.

## Conseqeuences of Not Using Singletons (When You Should)

The alternative to "singletons" is to instantiate the `ControlBoard` somewhere inside of `RobotContainer`, probably in the container's constructor. It thus "belongs to" the `RobotContainer`.

- Now, for some applications, this is a problem. If the "owning object" goes away, then its references are garbage collected. Meh... That's not that bad.
- Worse: the objects interested in accessing fields and methods of the `ControlBoard` **must** be able to access some `controlBoard` object! This implies that you pass around an instance...
- Suddenly, you must add `ControlBoard cb, ...` to whichever methods are interested in that `cb` object -- **OR** add `ControlBoard cb` as a field on objects that must access its methods/data.

Please **consider very carefully** the consequences of the above! If there cannot be a "singular" singleton instance, then:

- You will frequently add/remove the text `ControlBoard cb` from **either (1)** your class's instance method signatures **OR (2)** your class's constructor signatures.
- And this *frequent* change in method signatures makes testing difficult **and** causes changes to percolate through your codebase.

So, when it makes sense, singleton objects eliminate entire potential universes of refactors, pull requests and small changes here & there.

## Problems With Singletons (Advanced)

Some advanced cases where singleton objects are troublesome:

1. The objects that the singleton instance provides access to are used in complicated threading or performance-sensitive ways.
2. Objects are assigned to fields on the *singleton instance*, but should "belong to" other *owning object instances*. Where you may expect these to be garbage collected when those owning object instances go out of scope, the objects that were assigned to the *singleton instance* may not be garbage collected.
  - **To generally avoid this**, instantiate the singleton as `public static final mySingleton = mySingletonBuilder()`. The logic for creating the object should be determined during compilation (and executed early in application initialization).
