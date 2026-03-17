<!-- TOC -->

- [Overview](#overview)
- [Winget](#winget)
  - [Winget Usage](#winget-usage)
- [WPILib](#wpilib)
  - [Installing WPILib And VSCode](#installing-wpilib-and-vscode)
  - [Installing FRC Game Tools](#installing-frc-game-tools)
  - [Installing PathPlanner](#installing-pathplanner)
  - [Quick Test of the WPILib Installation](#quick-test-of-the-wpilib-installation)
- [Git and Diff](#git-and-diff)
  - [Test cloning a directory](#test-cloning-a-directory)
  - [Sourcetree](#sourcetree)
  - [Github CLI](#github-cli)
- [Apps](#apps)
  - [Docs](#docs)
  - [Note-Taking](#note-taking)
  - [Blender](#blender)
  - [Network Tools](#network-tools)
    - [NETworkManager](#networkmanager)
    - [PuTTY](#putty)
- [Windows Settings](#windows-settings)
  - [Settings](#settings)
  - [Power Toys](#power-toys)
    - [Powershell](#powershell)
- [Windows Security](#windows-security)
    - [Administrator Priviledges](#administrator-priviledges)
    - [Local Administrator Login](#local-administrator-login)
    - [Programs vs. Services](#programs-vs-services)
    - [Power Toys](#power-toys)
- [Advanced Software](#advanced-software)
  - [Docker](#docker)
  - [GnuPG](#gnupg)

<!-- /TOC -->

# Overview

This is a guide to generally setting up a new computer for WPILib Programming. If you have suggestions for additions or problems following the guide, please reach out.

To begin XRP Programming, only the programs listed in the `WPILib` section are required. Please install the tools listed in the `Git and Diff` section next. `git`, `Sourcetree` and `GithubDesktop` will be necessary soon after you get started.

There are tools described in the `Apps` section which will help assist in troubleshooting, learning and documenting. The Networking tools are not required, but are great ways to learn about troubleshooting PhotonVision, RoboRio and the radio.

There are some brief notes on `Windows Security` towards the end, as well as other `Advanced Software` that we can help you learn.

# Winget

The `winget` program is a "package manager" for windows. Most of the `winget install -e --id $appid` commands below are effectively the same as downloading that software from the Microsoft Store.

+ For most `$appid`, you could also find it in the Microsoft Store by searching for `$appid`.
+ Or you can see if that `$appid` exists by running `winget search $appid`

Winget is just a faster way to install software on a Windows system. You can run the `winget` command from `cmd`, `powershell` or `GitBash`. However, you shouldn't install software via winget if you don't know what it is -- i.e. you should probably look it up on Microsoft Store anyways.

## Winget Usage

### Troubleshooting `winget` Installs

Sometimes an app installation will stall out and seem to take forever. If this occurs, it's likely that the Windows `Secure Desktop` is opened in the background -- this asks you to "allow/deny" potential changes to your computer. You can either `Ctrl-C` to cancel the installation & run again. Or you can open task manager and look for the installation to get the `Secure Desktop` to pop-up into the foreground.

### `winget` Scope

On Windows 11, some of the `winget` commands may differ in whether you need `--scope machine`, which essentially installs the app for all users -- as you recall, many installers will prompt you to decide whether to install "only for your user" or "for all users". You almost never need this `--scope` option for the software below.

+ Generally, you should avoid `--scope machine` unless its necessary.
+ When included, it will also install the app for a local `Administrator` account.

> Please note that I tested these commands on Windows 10, where I have a local user only and where I tried to avoid app installations that would have administrator priviledges.

# WPILib

## Installing WPILib And VSCode

Follow the [WPILib Installation Guide](https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-2/wpilib-setup.html).

> NOTE: if you already have VSCode installed, then you typically need to run the WPILib-bundled VSCode. This app will have the WPILib Icon. Otherwise, Java and particularly the setup of a new Java or WPILib application may not work as expected. So ensure you're running the correct VSCode, especially when you're creating a new project.

## Installing FRC Game Tools

Follow the [FRC Game Tools Installation Guide](https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-2/frc-game-tools.html)

After installing this, try opening up DriverStation. If this app is not open, the project will run in the RobotSimulator instead.

## Installing PathPlanner

The easiest way to get this is from [Microsoft Store: FRC PathPlanner](https://apps.microsoft.com/detail/9nqbkb5dw909?hl=en-US&gl=US).

## Quick Test of the WPILib Installation

Make sure you can open these apps:

+ WPILib VSCode: edits and runs projects, either in RobotSimulator or via the DriverStation's connection to the robot.
+ ElasticDashboard: this allows us to adjust "Tunable Numbers", change the robot's mode and trigger autos.
+ AdvantageScope: visualizes logging and performance metrics. There are three key shortcuts that help troubleshoot.
  - `Ctrl-K` will connect to a running robot. `Ctrl-Shift-K` will connect to a running RobotSimulator.
  - `Ctrl-,` will open the settings.
+ PathPlanner: creates JSON files for paths. These JSON files get deployed to the RoboRio.
  - The files that PathPlanner saves are found in `src/main/deploy/pathplanner` in a project.

# Git and Diff

> NOTE: This guide on `git` should get the software installed on your machine. Read through [git/01-overview.md](./git/01-overview.md) after completing this guide.

These apps allow you to view a repositories branches, make commits and `fetch` changes from the repository.

```shell
winget install -e --id Git.Git --source winget
winget install -e --id Atlassian.Sourcetree --accept-configuration-agreements
winget install -e --id GitHub.GitHubDesktop
```

Now that you've installed `git` for windows, you should be able to right-click on a project's root directory to open it in `GitBash` or `gitk`, the GUI.

The Github Desktop tutorial provides a useful tutorial that walks you through the most basic steps. Run through that next if you've never used `git` before.

## Test cloning a directory

### Open the parent directory in `GitBash`

+ Open the folder where you want projects to be stored, then go to the parent directory.Right-click the parent directory and click `Open in GitBash`.
+ Or hit the windows key, open `GitBash` and `cd $projectsDirectory` where `$projectsDirectory` is where you want to clone a project.

### Clone a project in `GitBash`

Now that `GitBash` is open, try cloning a project. We can't yet clone the `Star-City-Robotics` without logging in. Run this command in `GitBash`

```shell
git clone https://github.com/MechanicalAdvantage/AdvantageKit
```

This will clone the `AdvantageKit` project into `$projectsDirectory/AdvantageKit`

When you clone a project, by default it creates a new folder named according to the Github URL. You can clone it somewhere else by giving it an argument. So this would clone the project to a folder on your desktop:

```shell
git clone https://github.com/MechanicalAdvantage/AdvantageKit /c/Users/YourUsername/Desktop/AdvantageKit
```

### Clone a `Star-City-Robotics` project

Cloning this project should prompt you for login details, which are handled securely by Git Credential Manager (GCM).

```shell
git clone https://github.com/MechanicalAdvantage/AdvantageKit
```

This actually requires a bit more setup later on to force a password prompt every 15 minutes or so. IIRC Git Credential Manager (GCM) will cache your password for either an hour or a day.

## Sourcetree

Two things to setup here: (1) the installation of Git to use and (2) Authentication for Github

### SourceTree: Installation of Git

Sourcetree bundles its own `git`, which forces authentication using SSH. This is too much for us. We need to use the Windows `git` instead.

+ Ensure that you've installed [git-ecosystem/git-credential-manager](https://github.com/git-ecosystem/git-credential-manager). This is included with `Git.Git` in the previous section.
+ Open `GitBash`, type `which git` and hit enter. Copy the path, which should end with `/bin/git`.
+ Open Sourcetree. Go to `Settings`. Click the `Git` tab and find the section that mentions a git executable. Paste the copied path.
+ If there's a file browser popup, you should be able to type `Ctrl-l`, paste it there and hit `Enter`, but this may require chopping off the `git` from `.../bin/git`, then selecting the file.

Setting the installation of `git` to use was required on Mac and I'm pretty sure it's required in Windows.

### SourceTree: Authentication for Github

Here are the official instructions on [Windows authentication](https://support.atlassian.com/sourcetree/kb/sourcetree-for-windows-1100-authentication-and-accounts-updates/)

Open `Settings`, then click the `Authentication` tab. Click `Add` and add your Github user/pass.

This can be tested by running `git fetch` with Sourcetree:

+ Open a repository in Sourcetree that you cloned
+ Under remotes, right-click `origin` and select `fetch`.

Anyone can `clone` or `fetch` the `MechanicalAdvantage/AdvantageKit` repository. Only when `Github Authentication` is set up can you fetch for `Star-City-Robotics/SCAROB-2026`. When using `git-credential-manager`, account set up in Sourcetree should not be necessary.

> TODO: This may not be correct. You should be able to `fetch` from here, but I need to see the app to be sure about the menu.

## Github CLI

The `Github.cli` app lets you run the `gh` commandline tool to list pull requests or issues. This likely needs to run from `GitBash`, instead of the standard `cmd` prompt in Windows.

```shell
winget install -e --id GitHub.cli # optional, but recommended
```

This `gh` may require that you set up some other components, but it should use the GCM to cache your user/pass.

## Meld

Meld is an app that lets you `diff` across directories. It's more of an advanced tool. For example, it lets you compare differences between WPILib or AdvantageKit templates, which we typically use at the beginning of a season.

```shell
winget install -e --id Meld.Meld
```

# Apps

## Docs

`PhotonVision` and `AdvantageScope` both make the docs very accessible in their applictions. Don't forget!

### Zeal

For other tools and programming languages, the `Zeal` app is a great way to get fast access to udpated docsets.

```shell
winget install -e --id  OlegShparber.Zeal
```

+ You'll need to configure `Zeal` with the specific docsets and so they are updated.
+ Keep in mind, that less docsets means searches are more concise. Activate the ones you're currently using.
+ For MacOS, there's the `Dash` app. `Zeal` can install/browse any Dash docset.
+ Dash is fantastic. If you're a developer with a Mac, it should definitely be on your machine.

## Note-Taking

We're using `Obsidian` for team notes and exploring syncing them with Git. This one's optional.

```shell
winget install --scope machine -e --id Obsidian.Obsidian
```

## Blender

Blender can open/view/convert most 3D formats. Use it if the Windows `3D Viewer` isn't enough. It doesn't handle `STEP` files from CAD though.

```shell
# I had trouble with installing this app via winget, specifically. better download from elsewhere
winget install -e --id BlenderFoundation.Blender
```

## Network Tools

These tools are useful for:

+ debugging your network config
+ checking the IP address on your Windows Ethernet & Wifi interfaces
+ checking the state of devices on the network

Any tool will require elevated permissions either when running it or installing it if the tool:

+ modifies network configuration or creates virtual network devices. The DriverStation and, IIRC, PhoenixTuner both create/modify virtual devices, so when you run them, they prompt you for permission with the Windows `Secure Desktop`.
+ modifies network state, like firewall rules, either on installation or when you run the app
+ accesses devices at a low-level. The WiFi Analyzer tool must directly interact with your WiFi card.

For security hygiene:

+ if you don't need a tool like this, you shouldn't install it
+ or you don't plan to use it again, you should uninstall it

For these tools, I'll clarify the intended use.

### NETworkManager

+ This is probably the only network tool we'll need on Windows. It's very powerful.
  - For WiFi issues, `WiFi Analyzer` is more useful, but download that from the Microsoft Store.
  - This is much better than AngryIPScanner, but also requires more priviledges.
+ [Docs](hhttps://borntoberoot.net/NETworkManager/docs/introduction)

```shell
winget install -e --id BornToBeRoot.NETworkManager
```

#### Features for FRC

+ Try to think of these features in terms of the *questions* that each can *answer*.
+ This are listed in order of importance from top-down, left-right (i.e. the top left is most important)

|           Feature | Usage                                                 |           Feature | Usage                                         |
| ----------------: | :---------------------------------------------------- | ----------------: | :-------------------------------------------- |
| Network Interface | View or Configure IP Address                          |        DNS Lookup | Test resolution of `DNS` names                |
|              WiFi | View Networks & Channels                              |       Web Console | Basically a browser                           |
|         ARP Table | Which MAC addresses correspond to which IP addresses? |            Lookup | `OUI` and `Port`                              |
|      Ping Monitor | Start pings to multiple hosts                         |       Connections | What computers/ports are you connected to?    |
|        IP Scanner | Find the RoboRIO                                      |         Listeners | On which ports/IPs is your computer listening |
|        Traceroute | Check the "route" that reaches a device               | Hosts File Editor | Force a `DNS` name                            |
|      Port Scanner | Check if PhotonVision is available                    |                   |                                               |

+ When juggling connections to multiple computers, like when configuring a router & multiple computers...
  - Start `Ping Monitor` with a host on each network.
  - **Then** start pulling & plugging your ethernet cables. Just leave the monitor running
+ The `ARP Table` is sometimes very useful (e.g. should I be able to ping the roborio/radio?).
  - It's towards the bottom and *answers* the *question*: "Do I have a Layer 2 problem? Or a Layer 3+ problem?"
  - ARP keeps track of which `Layer 3` IP Addresses correspond to each `Layer 2` MAC address.
  - Use this if you expect to be able to ping an IP address, but suspect some lower-level connectivity problem.
+ The `Lookup` tool: answers "Who makes that random device on my network?" Or "What's that port again?"
  - `OUI` takes the first 6 characters of a MAC address and tells you who makes that device.
  - `Port` translates port numbers.
+ DNS Lookup will be useful when trying to connect to a device with a `.local` name resolved via `mDNS`.
  - We will not *depend* on `mDNS` names at comp or elsewhere, but they will usually be available to ping.
  - `mDNS` names like `photonvision.local` and `roboRIO-10004-FRC.local` are simpler to remember.

#### Other Features

|            Feature | Usage                                               |        Feature | Usage                                         |
| -----------------: | :-------------------------------------------------- | -------------: | :-------------------------------------------- |
|  Subnet Calculator | Design a subnet                                     |    SNTP Lookup | `Simple Network Time Protocol`                |
|     Bit Calculator | Simple binary calculator/translator                 |          Whois | Check public DNS names                        |
| Discovery Protocol | `LLDP` & `CDP`. For routers/servers.                |     Powershell | Start a powershell session                    |
|               SNMP | `Simple Network Management Protocol`: Get, Walk,Set |       TigerVNC | Unused: requires `VNC`; mostly for Linux      |
|        Wake on LAN | Power on a computer via a network interface         | Remote Desktop | Unused: connect to Windows remotely via `RDP` |

+ `SNMP` is too advanced and rarely used, except in some cases for `Get` or `Walk`
+ `LLDP` (open source) and `CDP` (Cisco-proprietary) are useful, but require that your network is configured for this.
  - Running commands for these helps answer questions like "What devices do you know about?"

### PuTTY

In technical jargon, `PuTTY` is a *terminal emulator*. `PuTTY` allows you to connect to a computer's command-line interface via protocols like `ssh`, `telnet` and a few others. It's difficult to set up an `ssh` server on a Windows machine, so `PuTTY` makes more sense when you're connecting to Linux servers or, in some cases, IoT devices.

```shell
winget install -e --id PuTTY.PuTTY
```

We can run `ssh` in `GitBash` or in `PuTTY`. We would use `PuTTY` to connect to:

+ PhotonVision: Then run `ssh photon@10.100.4.11` and enter `vision` as the password. You can now run any Linux CLI tool installed on the Orange Pi.
+ RoboRio: Similarly, we could connect to the RoboRIO device with `PuTTY`.

For `PuTTY`, it's easier to keep a list of login details and connection settings for computers that you connect to. e.g. you can have save the connection details for:

- the RoboRio on the `COMPBOT` or `DEVBOT`.
- the OrangePi on the `COMPBOT` or `DEVBOT`.

For the most part, we'll use the web applications to connect to these. If you ever want to learn about `ssh`, now is a good time because David has a lot of experience configuring the client, the connections, the server and the algorithms used.

# Windows Settings

## Settings

## Power Toys

### Powershell

This is optional software and should not be installed unless you plan on using it. It's a great way to automate your Windows system with scripts and to learn some of the `.NET` APIs.

There is some software which would require upgrading Powershell to a more recent version. Some features of Power Toys require Powershell `7.4.5` -- mainly "Command Not Found", which I would actually recommend disabling anyways.

You can install it via `winget` with the following:

```shell
# you only need to run this if you need to upgrade powershell. Windows 10 and 11 already have it
winget install --id Microsoft.PowerShell --source winget
```

With powershell, there are two key takeaways beginners should keep in mind if they choose to learn it:

+ Most of the basic commands you run are actually references to core `.NET API` endpoints.
  - So the "vocabulary" of Powershell is very similar to that of `C#`'s core `.NET` APIs.
+ Powershell *commands output their results as tables.* This allows you to **join result-sets** *like you would join SQL tables*.
  - This is a key feature of powershell which isn't available by default for linux commands -- with the exception of the Unix `join` command... which is basically useless

# Windows Security

This is a brief overview of some concepts regarding windows security. Keep in mind that I don't know much about windows. So you may need to do your own research.

### Administrator Priviledges

This is distinguished from the `login` capability below. Some apps that WPILib includes require elevanted permissions. Any application that modifies network configuration or interacts directly with devices will require approval via the Secure Desktop.

### Local Administrator Login

There are many guides online which may instruct you to enable this `Administrator` account for login, but if you do, you need to ensure that its password is set and that it's secured. Therefore, you should avoid enabling that account. In General, this `Administrator` account should, whenever possible, be disabled for login. Installing as `--scope machine` would likely install the app for `Administrator` -- even though that account remains disabled for login (IIRC). However, were login later enabled, then the additional capabilities of those applications need to be considered.

### Programs vs. Services

When concerned about whether an app remains installed on a Windows installation, the `Add/Remove Programs` menu's do a pretty good job of cleaning up. If you want to remove something, then a well-behaved application can usually be removed here, though you need to understand whether there are multiple menu entries here. This is one reason why installing applications under a single user is a better idea.

### Power Toys

These are great tools, but they involve UI automation. Like apps for accessibility, anything that automates UI will typically require some elevation. In Windows, most of the tools in Power Toys accomplishes this without needing to explicitly elevate.

There's one main issue where a popup appears letting users know something failed. A Power Toy tried to manage the window arrangement, etc:

+ while the Windows `Secure Desktop` popup was displayed
+ or while an app was running with elevated permissions

Power Toys can't do this without elevated permissions, so it warns you. There are some guides that will tell you to do this (i.e. to avoid annoying pop-ups). If you install this **never** run it with elevate permissions. Either disable the Power Toys project that creates those pop-ups or deal with the pop-ups. **Do not** run power-toys as administrator: it's an app that runs services which do not close. They would all have elevated permissions.

# Advanced Software

## Docker

We likely won't be using Docker, but if you want help learning it, David and Jon can help you out. This is a fairly advanced tool and the WPILib ecosystem tools specifically try to avoid making it a dependency (e.g. PhotonVision does not release docker images).

Please reach out before installing on your own: there are some setup/maintainence tasks that you'll want to know about.

Docker allows you to quickly run bundled application "images" like JupyterLab or AI agents (requires GPU, usually)

```shell
winget install -e --id Docker.DockerDesktop
winget install -e --id Docker.DockerCLI
```

IIRC, Docker requires setting up `wsl2`.

### WSL

```shell
wsl --install
# wsl --update # later, when you need to update

# list the distros on your machine
wsl --list

# list the distros that you can download
wsl --list --online

wsl --install -d Debian # or some $distroname
```

+ TODO: What to configure in settings (for Docker, Hyper-V)

## GnuPG

> NOTE: this is absolutely optional and we won't use it. However, if you ever wanted to learn about GnuPG, this GUI will help a ton.

- it is widely used for Linux, though `AGE` encryption is more important to understand for devops
- understanding GPG key infrastructure is helpful for managing extra software found in Ubuntu/Debian PPAs

This installs an app called `Kleopatra` which makes it easier to learn about:

- managing GPG keys
- signing/encrypting emails
- managing encrypted documents

```shell
winget install -e --id GnuPG.Gpg4Win
```

> NOTE: one should also know that using these tools securely requires expert-level knowledge. Otherwise, without proper management of data, there are no protections offered by any cryptography. This is also a tool that David knows a ton about.
