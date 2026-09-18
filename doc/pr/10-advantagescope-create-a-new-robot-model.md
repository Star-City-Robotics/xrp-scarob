# AdvantageScope: Create A New Robot Model

[PR 10](https://github.com/Star-City-Robotics/xrp-scarob/pull/10)

This walks through creating custom assets for simulation in AdvantageScope. This allows us to create more complicated XRP simulations or try out prototypes for 3D printed parts.

# Resources

[CAD Assistant](https://dev.opencascade.org/project/cad-assistant): download and install.

XRP Robot Kit CAD:

+ [Printables](https://printables.com/model/1216372-xrp-robot-kit)
+ [Original Onshape Document](https://cad.onshape.com/documents/9bf1dfe68ee7a0325b55874b/w/ce3df1692e8bea96311ada3c/e/74b13b5b748084e43addbe27)

AdvantageScope:

+ [Custom Assets](https://docs.advantagescope.org/more-features/custom-assets/). This guide describes positioning the custom assets within AdvantageScope
+ [Converting Onshape & STEP Files to glTF](https://docs.advantagescope.org/more-features/custom-assets/gltf-convert). This guide's screenshots are a bit out of date, which is to be expected.

# Onshape

> Note: David is not an authority in Onshape or CAD

## Export As STEP

First, plan the export

+ Which sets of parts need to be exported as groups? Typically, each set of parts that would move as a group needs to be a separate STEP export.
+ Are there highly detailed parts like circuit boards? These should typically be left out, if possible.

> It's really important that the STEP files are exported from the same assembly, so all
> the exports exist in a unified coordinate system.

For each group of parts:

+ Open their assemblies, note their relationship to the assemblies' origins
+ Examine each group's associated mates
  - The types and sets of mates will typically correspond to the structure of `rotations` and `position` assigned to the part in the AdvantageScope assets JSON.
  - These parts' mates clue you in to the transformations necessary to reproduce motion.
  - Especially, take note of any mates with constraints.

### Manage The Document

Before export, think about how to manage the document(s).

+ If you have edit access, would the export process affect the assemblies & part studios? Hiding, showing and moving parts would sometimes be considered an edit. If you only view & export the document, you probably don't want `Edit` access.
+ By accessing the document, would you lock it to other users?
+ Do you need to export from multiple separate Onshape Documents? It's possible you may need to add assemblies or part studios in order to export parts combined together, especially if multiple STEP files need to be in the same STEP export.

If any of these are true, it may be best to copy the Onshape Workspace into a new workspace that retains the same visibility and organizational details.  Again, there really needs to be single assembly.

How would you repeat the process in the future? If this is necessary, you *may not* want to create a branch, but I'm not sure how & whether that locks the document.

Each group of parts would need to be in the same assembly in order to export. If a branch or new document is created, repeating the process in the future is more difficult. Usually, there's no way to avoid repeated work. If they're in the same assembly, the process is as simple as selecting *parts* and exporting. If STEP files must be converted to `glTF` and then combined, this is much more difficult: you may lose the unified coordinate system, for example.

### For Each Group of Parts, Export A STEP

+ Select the parts from `Instances (n)` and only those parts.
+ Right click. Click `Export...`.
+ Use the same prefix for the filename for each export. Replace any spaces with dashes.
+ Choose `STEP`. Typically, select `None` for `Preprocessing`.
+ For FRC, leave the `Z` axis up, unless necessary. This should be unified on part import to assembly by the Document author.
+ If you must choose `custom units`, choose wisely.
+ Uncheck `Export unique parts as individual files` unless it's necessary. This gets you a single `STEP` file. Otherwise, it's possible that the coordinates for each part are not unified.

# CAD Assistant

Open CAD Assistant

## Convert to `glTF`

> The `*.glb` files should total less than `10MB` if stored in a repository. They are binary and `git` will retain each change as individual copies of the files.

For each exported `STEP` file:

+ Click open, select the file, open it
+ Click save, change `STEP` to `glb`, which is `glTF`'s binary format
+ Click the gear, toggle `Merge faces within the same part`
+ Click save. For FRC projects, save in `./ascope/assets/$model` where `$model` contains all the `glb` files exported for this robot, game piece or field.

In some cases, if there are more than `2^14` triangles, then `Merge faces with 16-bit indices limit` may help. AdvantageScope tries to further reduce the mesh anyways. Keep it simple.

Clicking the top-level instance for the XRP wheels originally exported the sourced Part Studio, which included neither the o-ring tire nor the mirrored part instance.

+ This could be fixed in the `XRPMechanism` code. It's possible that rotating the part in code would either cause one wheel to drive backwards or overcomplicate the code.
+ Instead, the parts were re-exported by clicking each wheel instances' sub parts.
+ The o-ring was omitted because it's instance was hidden in the wheel's part studio

## Organize The Assets

Each `glb` part on the main model has `rotations` and `position`. AFAIK, only the top-level `glb` can have child `glb` models associated to it -- i.e. it's a flat tree.

|           STEP source | Part          | File        |
| --------------------: | :------------ | ----------- |
|      XRP-chassis.step | chassis       | model.glb   |
|   XRP-wheel-left.step | left wheel    | model_0.glb |
|  XRP-wheel-right.step | right wheel   | model_1.glb |
|        XRP-servo.step | servo & mount | model_2.glb |
|          XRP-arm.step | arm           | model_3.glb |
|  XRP-line-sensor.step | line sensor   | model_4.glb |
| XRP-sonar-sensor.step | sonar sensor  | model_5.glb |

The parts need to be renamed as `model.glb` and `model_n.glb`. This is confusing, so the original parts are in `./ascope/assets/xrp-bot-source`. The final parts need to reside in a directory prefixed by `Robot_`, so these are in `Robot_XRPBot`.

# Check `*.*glb` Files In Blender

> The WPILib geometry classes like `Pose3d` and `Transform3d` are defined fully
> further down. Checking the models in Blender is highly recommended, since
> recompiling java and restarting the simulator is a bit expensive.

+ Open blender and start a new project. Find the `Outliner` scene graph tool that displays the node tree of your project's assets.
+ Open a file browser and navigate to the exported `model_*.glb` files
+ Drag them into the top level of the `Outliner` tree.

Each imported model will have an `Origin` associated to its `Frame`. There are "rays" cast from that part's origin to other reference points. The origin is highlighted with a special ring symbol.

+ Clicking through the top-level objects in the `Outliner` node tree will show you their origins.
+ The assets are initially added to the project such that their origins are at a common point.
+ It's possible to measure the vector's that separate each `*.glb`'s origin -- in meters by default. Regardless of the options selected for `STEP` export, this should give starting values for `config.json` tuning the `Mechanism3d` later on.

At this point, consider the `transform`, `translate` and `rotate` operations to get each mechanism into place when building the `Mechanism3d`.

+ Single rotations at the origin are fairly simple, esp. for a single-axis.
+ Plan to keep each model at the origin and, if possible, perform a single transform afterwards ***when** you create the `new Pose3d(transform, rotate)`.
+ If a rotation and transform are needed, the rotation should be generally done first.

## `transform`, `translate` and `rotate`

This content is included so we have a few team members that can independently visualize the robot in the simulator. One objective here is to introduce the basic objects & classes for "Pose Estimation" later.

These concepts are widely used in applications that handle 3D data, including:

+ Games and graphics: the scene graph, projection matrices and collision detection
* CAD and 3D modeling: animation and part origin
* Robotics: URDF, kinematics chains and *pose estimation*
* Orbital mechanics and astrophysics: frame conversions & barycentric coordinates

While these are challenging problems and require in-depth calculation by hand, it's still perfectly possible to `import * from scipy` so to speak and then work with these tools in code -- without needing two years of college engineering math.

However, please note that if the primary concepts aren't understood, this is a terribly confusing way to learn. Debugging the `Mechanism3d` code gets messy If semantics aren't clearly understood, you may find yourself "unlearning."

### Background

> When referred to using the lower-case, this denotes a `pose` or `transform` in
> the general sense (a very general sense). When referring directly to the proper
> `Pose2d` or `Transform3d` classes, this denotes the specific WPILib implementation.

Both a `Pose3d` and a `Transform3d` are defined as the composition of a `Translation3d` and a `Rotation3d`. Not only are they completely different, but each implementation of these math objects vary depending on the library and it's applications' problem domains.

Briefly:

A `transform` converts a frame for points in one local space to the bases for another local space's frame.

+ A `transform` is the composition of a `translation` and `rotation`, where the *rotation* is performed **first**. Orient towards the target frame (rotate), then move to it (translate).
+ This can be done as a single matrix multiplication of two `4x4` matrices with special values. Or the `rotation` and `translation` can be extract and performed individually.

A `pose` also composes these, but it refers to a **state**:

- e.g. states of objects on the FRC field or individual components of a complex mechanism.
+ As a state that needs position and orientation -- like heading or facing -- the `rotation` for a pose is performed **after** the translation.
+ Most operations that make sense for a `transform` or `translation` don't make sense, per se, for a `pose`. So you can't naively add them
+ This is an important distinction: once you have a pose, you need to "decompose" it into the individual `translate` and `rotate` in order to get the expected results if operating on them or adding their translations.

### WPILib Geometry Semantics

In WPILib (and elsewhere) working with `Pose2d` or `Transform2d` is trivial in that, even if it's challenging at first, these objects behave as you'd expect. Rotations in 3D are complicated.

Another important assumption in WPILib is that *all* `Pose` objects are assumed to be in the global frame (the field's coordinates). AdvantageScope considers the pose for a `model.glb` to be in the global frame and all `model_*.glb` components to be in the `model.glb` frame.

The semantics of WPILib's `Pose3d` and `Transform3d` objects are made even more complicated by:

+ The origins of `glb` models are invisible inside AdvantageScope, once editing the `Mechanism3d` class
+ The global frame of`Pose3d` complicates simple rotations.
+ Tracing through the object-oriented `WPILibJ` source with `F12` leads to many files and methods.

# Configure The Model

Once imported, AdvantageScope watches for changes to `config.json` and will redisplay the model when the files change. This occurs any time you change: `rotations`,  `position`, `zeroedRotations`, `zeroedPositions`.

It also reloads when the individual `*.glb` files are changed, it's sufficient to toggle the robot between models in AdvantageScope.

Keep in mind **rotations** are applied *before* **positions**, where the latter corresponds to the translations.

## Setup The JSON

In `./ascope/assets/Robot_XRPBot`, create `config.json`. Start with empty arrays for `cameras` and `components`:

```jsonc
{
  "name": "XRP Bot",
  "isFTC": false,
  // AdvantageScope culls most of the model, as it's too small
  "disableSimplification": true,
  "position": [0.0, 0.0, 0.045],
  "rotations": [
    { "axis": "x", "degrees": 90 },
    { "axis": "z", "degrees": 90 }
  ],
  "cameras": [],
  "components": []
}
```

Then for each `*.glb` file, add a default entry in `components`. Ensure all comments are removed from the JSON files.

```jsonc
// components: [
{
  "zeroedRotations": [
    { "axis": "x", "degrees": 90 },
    { "axis": "z", "degrees": 90 }
  ],
  "zeroedPosition": [ 0.0, 0.0, 0.0 ]
}
// , {...}, ... ]
```

Since all of the STEP file's parts were exported from the final assembly, then all of their exports have unified coordinate systems. However, the `glTF` format has different coordinate conventions, so the `90°` rotations are required.

| STEP | glTF | WPILib | Direction |
| ---- | ---- | ------ | --------- |
| Z+   | Y+   | Z+     | Up        |
| Y+   | X+   | Y+     | Left      |
| X+   | Z+   | X+     | Forward   |

## Set Zeroed `Pose2d` for the Robot

In our competition code, `Drive.java` also exports the position using the `Odometry/Robot` key.

```java
   @AutoLogOutput(key = "Odometry/Robot")
   public Pose2d getPose() {
     return Pose2d.kZero;
     // return poseEstimator.getEstimatedPosition();
   }
```

We'll set the estimated pose i  a later section, after completing the drivetrain and adding some simulation logic ,

## Import into AdvantageScope

+ Copy the `ascope/assets/Robot_XRPBot` directory into `~/.config/AdvantageScope/userAssets`
+ When settig up the `Mechanism3d`, edit one copy of files to make adjustments, then commit.
+ Set the field to `Evergreen` or `Axes`

# Mechanism3d

## Testing

Again, recompiling and testing is expensive. Changing the `config.json` once it's correct doesn't help much.

## Design a class

Ideally, this class doesn't need much state. As more private fields are added to classes in Java, the potential for bugs generally increases, especially when it's a distributed application or when threads are involved.

## WPILib Geometry

# Advanced

## Rendering in AdvantageScope

When the a pose is logged for a `component` or `camera`, this transformation is calculated after `zeroedPosition` or `zeroedRotation` are set by rendering in AdvantageScope's typescript. The "zeroed" settings are intended to adjust components to the robot's origin. If the STEP was exported from a single document, only the `90°` corrections for `glTF` are necessary.

See [src/shared/renderers/field3d](https://github.com/Mechanical-Advantage/AdvantageScope/blob/abb616bdd575dfa27f5794ed6be427ffd0209897/src/shared/renderers/field3d):

+ [./workers/loadRobot](https://github.com/Mechanical-Advantage/AdvantageScope/blob/abb616bdd575dfa27f5794ed6be427ffd0209897/src/shared/renderers/field3d/workers/loadRobot.ts#L49-L52)
