# XRP Scarob

## Resources

+ [AdvantageKit Sources](doc/pr/img/FRC-NetworkTables.pdf): These sources are available after setting up & merging the AdvantageKit TalonFX and PhotonVision templates.
+ [Robot Simulator Keyboard Bindings](doc/pr/img/FRC-RobotSimulator-Controls.pdf): as set in this project's simgui-ds.json
+ [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html): explanations on conventions for variable names and basic programming practices

## Course

For now, everything is set up around the pull requests that created the code. This encourages team members to read the code in the pull request. This isn't always practical, as some pull requests are larger & more unwieldy than I'd like -- see $$\S 3$$ on formatting.

It's also, unfortunately, not organized according to what is beginner friendly. This table should help team members find the sections that are most appropriate to begin next. See [Organization](#organization) for more info on what everything means.

| $$\S$$          | Value                          | Level                    | Categories                   |
|:----------------|:-------------------------------|:-------------------------|:-----------------------------|
| [1](#section-1) `**` | :star:                         | :star:                   | :thermometer: :video_game:   |
| [2](#section-2) `*` | :star::star::star:             | :star::star::star:       | :bar_chart:                  |
| [3](#section-3) `*` | :star::star:                   | :star::star:             | :thermometer: :octocat:      |
| [4](#section-4) `*` | :star::star::star::star::star: | :star::star::star:       | :bar_chart: :gear: :compass: |
| [5](#section-5) `*` | :star:                         | :star:                   | :thermometer:                |
| [6](#section-6) `**` | :star::star::star::star:       | :star::star::star::star: | :coffee: :video_game:        |
| [7](#section-7) `*` | :star::star:                   | :star:                   | :thermometer: :books:        |
| [8](#section-8) `*` | :star::star::star::star:       | :star::star::star:       | :coffee: :video_game:        |

> `*` Needs a draft
> `**` Needs to be edited

Eventually, this system will probably transition towards reorganizing the content into a course in [Star-City-Robotics/robo-dojo](https://github.com/Star-City-Robotics/robo-dojo). For now, it's just simpler to lay this out alongside the code.

#### Advanced Topics

+ If you're a beginner, skip sections labeled advanced. Consider them to be a sidequest for later.
+ If you're not a beginner, some of this content may not be worthwhile to learn during the on-season.

Some of these advanced skills are incredibly valuable, but many other tools cover these bases. CLI is great: balancing CLI usage with GUI early on will help you learn much faster.

#### Categories

|                    | Category       | Summary                                     |
|--------------------|----------------|---------------------------------------------|
| :video_game:       | Controls       | Controllers, `Command` and `Trigger`        |
| :bar_chart:        | AdvantageKit   | Logging & Setup for AdvantageScope          |
| :world_map:        | Pathplanner    | Automations and Paths                       |
| :compass:          | Sensors        | Reading from sensors                        |
| :gear:             | Motion         | Activating motors or responding to motion   |
| :coffee:           | Java           | Focuses on Java design patterns or features |
| :triangular_ruler: | Math           | Vectors, Rotations, Translations, etc       |
| :control_knobs:    | Control Theory | `PIDController`, tuning, etc.               |
| :camera:           | Vision         | `PhotonVision`, which can be done with XRP  |
| :game_die:         | Statistics     | `PoseEstimator`                             |
| :books:            | Docs           | Documentation and Guides                    |
| :octocat:          | Git & Github   | Commits, merging, rebasing, etc.            |
| :dependabot:       | Automation     | Github Actions or scripts                   |
| :thermometer:      | Boilerplate    | Small commits for project functionality     |

## Getting Started

Start with [doc/windows-setup.md](./doc/windows-setup.md) if you haven't configured installed WPILib yet. This also includes other recommended software tools

### Set up WPILib

Find the [most recent WPILib Release](https://github.com/wpilibsuite/allwpilib/releases/tag/v2026.2.1) on their Github "Releases" page. From there, follow the [WPILib Installation Guide](https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-2/wpilib-setup.html).

Before you write code, check out and open some of the apps.  They should be added to your OS's start menu. They can also be launched from within VS Code if they need a "Project Context." The most important ones for us are: ElasticDashboard, AdvantageScope,

If you'd like, read the [New for 2026 notes](https://docs.wpilib.org/en/stable/docs/yearly-overview/yearly-changelog.html) for the season. This is great for returning team members.

You'll also need PathPlanner, installed separately by following [this guide](https://pathplanner.dev/gui-getting-started.html).

Another important app is PhotonVision. This runs a web server and it's a bit hard to use without a field & AprilTags. Fortunately, we can simulate this from within the Java code. If a project that should contain simulated photonvision code is compiled and started, then you should be able to see the video stream at [localhost:1181](http://localhost:)

> Note: normally you wouldn't run all these apps with an XRP project, but we're working on building a framework that *should* be simple to use while also allowing you to practice using these other apps.

### Set up `git`

Download and setup Git. Here are official guides for: [Windows](https://git-scm.com/install/windows), [Mac OS](https://git-scm.com/install/mac), and [Linux](https://git-scm.com/install/linux).

The guide in [doc/windows-setup.md](doc/windows-setup.md) describes how to install almost everything using `winget`. This may be faster.

### Set up a `git` GUI

+ Download a Git GUI

|                | Windows | MacOS | Linux | CLI | Free     |
| -------------: | :------ | :---- | :---- | :-- | -------- |
| Github Desktop | :+1:    | :+1:  |       | :v: |          |
|     Sourcetree | :+1:    | :+1:  |       |     |          |
|     Git Kraken | :+1:    | :+1:  | :+1:  |     | :+1: `*` |

> `*` Git Kraken is free

## Course Sections

Each section has two links:

+ On the left is a link to a pull request.
+ On the right is a link to the markdown in this project that explains it.

#### Section 1

[PR 01](https://github.com/Star-City-Robotics/xrp-scarob/pull/1) $$\longrightarrow$$ [Configure the Robot Simulator's Keybindings](doc/pr/01-configure-the-robot-simulators-keybindings.md)

Read through this for an explanation of mapping controls in the simulator, especially if you need to map the keyboard controls.

#### Section 2

[PR 02](https://github.com/Star-City-Robotics/xrp-scarob/pull/2) $$\longrightarrow$$ [Merge Mechanical Advantage's XRP Example](doc/pr/02-merge-mechanical-advantages-xrp-example.md)

This PR merges Mechanical Advantage's XRP example, which sets up an XRP that can output logs to be visualized in AdvantageScope. However, their code needs some changes, as IMO it was intended for new team members to implement some of the controls on their own.

#### Section 3

[PR 03](https://github.com/Star-City-Robotics/xrp-scarob/pull/3) $$\longrightarrow$$ [Configure An FRC Project To Autoformat](doc/pr/03-configure-an-frc-project-to-autoformat.md)

This merges autoformatting, which minimizes changes and helps to make PR's more readble. Even better: it reduces the chances of merge conflicts when using Git on a team. This is usually considered "too much" for beginners, but since we need everyone to use Git, we definitely want to minimize merge conflicts.

#### Section 4

[PR 04](https://github.com/Star-City-Robotics/xrp-scarob/pull/4) $$\longrightarrow$$ [Spec out Drive Subsystem](doc/pr/04-spec-out-drive-subsystem.md)

This composes DrivetrainIO and GyroIO. The functionality is incomplete, However, as the code that accesses motors and sensors needs to be rewritten so that hardware access is as contained inside the **IO Interfaces** as possible.

It's also missing **Pose Estimation** and **Differential Drive Controller**.

#### Section 5

[PR 05](https://github.com/Star-City-Robotics/xrp-scarob/pull/5) $$\longrightarrow$$ [Include UML Diagrams In Javadoc](doc/pr/05-include-uml-diagrams-in-javadoc.md)

This is a small change to generate UML diagrams for code. Those can really help to explain design changes or functionality.

#### Section 6

[PR 06](https://github.com/Star-City-Robotics/xrp-scarob/pull/6) $$\longrightarrow$$ [Create a ControlBoard using Interface Composition](doc/pr/06-create-a-controlboard-using-interface-composition.md)

This is the first PR to really focus on Java itself. This introduces a *design pattern* called *Interface Composition*, which is useful.

... but TBH it's not the best design pattern. It's a bit constraining if used improperly. Here, it allows us to define game controller logic that can be implemented by a combination of controller implementations. Specifically, by changing `Constants.KOperator.kForceDriveGamepad`, a single line, we can split the controls into one gamepad each for the driver and operator.

#### Section 7

[PR 07](https://github.com/Star-City-Robotics/xrp-scarob/pull/7) $$\longrightarrow$$ [Adding Docs](doc/pr/07-adding-docs.md)

#### Section 8

[PR 08](https://github.com/Star-City-Robotics/xrp-scarob/pull/8) $$\longrightarrow$$ [Edit Docs To Add Windows Setup](doc/pr/08-edit-docs-to-add-windows-setup.md)

#### Section 9

[PR 09](https://github.com/Star-City-Robotics/xrp-scarob/pull/9) $$\longrightarrow$$ [Using Singletons to Access The Controlboard](doc/pr/09-using-singletons-to-access-the-controlboard.md)

This is the second PR to focus on Java programming -- and still fairly advanced. This introduces the **Singleton design pattern**, which we can use to access *some objects* from **anywhere** in our code.

### Organization

#### Level & Value

Level and Value are from 1 to 5, though both are in the eye of the beholder.

- Level estimates the difficulty, but more from a user's perpective. Designing from blank canvas is more difficult.
- Value estimates how useful it is for a team member to at least understand or maybe to use independently.

> For example, David considers `UML` to be very valuable and the PR here was
> simple. Whether its valuable depends on whether you use `javadoc` and `UML`.
> It's only valuable if people use it. Also, as someone who's new to Java,
> originally finding the UML solution was non-trivial. Adding it to a
> `build.gradle` is simple.

Similarly, the `docs` PR is only given two stars.

> This is a hot take: docs are great and infinitely valuable -- but only when
> people read them. The key is to have the right amounts of the right kinds of
> documentation in the right places. Technical writing is difficult. Consider
> the differences between tutorials, stack overflow, *man pages* and *info
> manuals*.

#### Categories

|                    | Category       | Summary                                     |
|--------------------|----------------|---------------------------------------------|
| :video_game:       | Controls       | Controllers, `Command` and `Trigger`        |
| :bar_chart:        | AdvantageKit   | Logging & Setup for AdvantageScope          |
| :world_map:        | Pathplanner    | Automations and Paths                       |
| :compass:          | Sensors        | Reading from sensors                        |
| :gear:             | Motion         | Activating motors or responding to motion   |
| :coffee:           | Java           | Focuses on Java design patterns or features |
| :triangular_ruler: | Math           | Vectors, Rotations, Translations, etc       |
| :control_knobs:    | Control Theory | `PIDController`, tuning, etc.               |
| :camera:           | Vision         | `PhotonVision`, which can be done with XRP  |
| :game_die:         | Statistics     | `PoseEstimator`                             |
| :books:            | Docs           | Documentation and Guides                    |
| :octocat:          | Git & Github   | Commits, merging, rebasing, etc.            |
| :dependabot:       | Automation     | Github Actions or scripts                   |
| :thermometer:      | Boilerplate    | Small commits for project functionality     |

These are loosely ordered by precedence.

+ :video_game: Controls, :bar_chart: AdvantageKit and :world_map: Pathplanner are some of the most important areas for programmers on the team to understand. We'll need as many team members as possible who can wear these hats. These are great places for beginners to start.
+ :books: Docs, :octocat: Git & Github, :dependabot: Automation and :thermometer: Boilerplate are good to understand (and sometimes essential) --  but these are roles/needs that are best left to a handful of people. They can affect many team members, but can require a lot of time or result in changes that aren't as important to the end users.
