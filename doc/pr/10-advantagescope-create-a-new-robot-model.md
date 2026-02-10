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

Then for each `*.glb` file, add a default entry in `components`

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

Ensure all comments are removed from the JSON files.

Since all of the STEP file's parts were exported from the final assembly, then all of their exports have unifed coordinate systems.

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

The XRP Robot is tiny.
