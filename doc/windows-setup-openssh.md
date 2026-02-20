# Windows Setup: OpenSSH

## OpenSSH

> NOTE (to self: don't commit this yet... idkwtf i'm talking about)

### Resources

+ [Microsoft: OpenSSH Docs](https://learn.microsoft.com/en-us/windows-server/administration/openssh/openssh-overview)
+ [The PowerShell/Win32-OpenSSH Wiki](https://github.com/PowerShell/Win32-OpenSSH/wiki)

#### Hardening

+ [JuliusBairaktaris/Harden-Windows-SSH](https://github.com/JuliusBairaktaris/Harden-Windows-SSH): A powershell script. Read through the source first. Any hardening of `sshd_config` should really be done by editing the file as an administrator, though CLI options are available. Other aspects of system security w.r.t. SSH may be more appropraite using CLI and/or GUI to validate
+ [HotCakeX/Harden-Windows-Security](https://github.com/HotCakeX/Harden-Windows-Security)

### Installing `OpenSSH`

Whereas `rdp` gives desktop access to remote users, `sshd` gives command-line access. It should only be set up after researching if you consider your desktop computer to be a server.

```shell
winget install -e --id Microsoft.OpenSSH.Preview
```

SSH enables `scp` and `rsync` and these tools help transfer files. Most power users would prefer a service like `syncthing`.

> Shell scripts for login and environment set up run before you see a login
> prompt. It's more difficult to configure these on Windows without resorting to
> jumping to another shell like `bash`. For domain accounts, use `Domain\user@host`
> 
> Unless you're a powershell user without a network on a Domain, then it's not
> clear to me how valuable ssh access is. Jumping to another shell impedes
> automation via `ssh user@host "command_to_run --with --options"`

### Configuring `sshd`

#### Starting the `sshd` Service

By running this, Microsoft's OpenSSH service will start when automatically Windows starts (before you login) until disabled. This needs to run in an administrative powershell session.

```powershell
# This runs via powershell and will definitely prompt for credentials
Start-Service -Name sshd
Set-Service -Name sshd -StartupType Automatic # use -StartupType Automatic
```

This generates the host keys under `%programdata%\ssh` if they don't already exist. If you needed to regenerate them, restart the server afterwards... and update your host keys on all connecting machines. Also open the firewall.

```powershell
netsh advfirewall firewall add rule name=sshd dir=in action=allow protocol=TCP localport=22
```

Other firewalls must be configured:

+ If there's an intermediate firewal that enforces rules between subnets, like VyOS, PFSense or OPNSense, this should be updated. Most networks will pass traffic for port `22`
+ If there are intermediate routers or devices are on multiple subnets, you must ensure the traffic paths are routable.

#### Hardening `sshd`

> Hardening should **not** be considered optional.

There are usually hardening tasks required for this service. These include:

+ Securing access to **only** the intended users. On Linux, this means disabling root access. On windows, it's more difficult to guarantee super-users cannot log in.
+ Restricting password authentication: you should generally never set `sshd` to start automatically if passwords are allowed. If temporary while learning to use SSH, this is fine. 
  
Most Linux desktops have a single user, whereas Windows desktops are often shared with users with easily guessed passwords. Most windows desktops mix Domain and Local authentication methods. These concerns and the lack of utility.

### Configure `ssh` for a user

To test that `ssh` is configured correctly, from non-administrative `Powershell`, `cmd` or `GitBash` run:

```shell
ssh localusername@localhost dir # or ipconfig
```

This will ask for your password -- disable that later! -- then run `dir` in `C:\Users\localusername`.

#### Copy Files from Remote Hosts

When testing `scp`, I could only specify windows paths from non-admin `Powershell` and `cmd`.

```shell
scp 'localusername@localhost:C:\Users\localusername\Desktop\desktop.ini' 'C:\Users\localusername\Desktop\desktop.test.ini'
```

This doesn't work from `GitBash` where `/usr/bin/scp` refers to a different binary. 

Typically, `rsync` should always be used instead.

> RSync needs to be installed *into the windows environment*, which is why i've
> stuck to `winget` thus far. Native, first-party environments are much simpler
> until third-party quick-fixes break things.

##### TODO: test pushing/fetching files from linux

#### Setup keys & hosts

+ SSH requires specific file permissions. See [OpenSSH Utility Scripts To Fix File Permissions](https://github.com/Powershell/Win32-OpenSSH/wiki/OpenSSH-utility-scripts-to-fix-file-permissions) in the wiki linked above.

Configure these files in SSH.

#### Setup Terminal

See [TTY/PTY Support In Windows OpenSSH](https://github.com/Powershell/Win32-OpenSSH/wiki/TTY-PTY-Support-In-Windows-OpenSSH) for instructions on terminal setup in various directions:

+ Windows to Linux/MacOS
+ Linux/MacOS to Windows

For MacOS and Linux, The `bash` and `zsh` setup are actually different. For all of these including Windows, most of the configuration effort should be on the connecting host as long as `/etc/profile` sets things according to `man terminfo` and terminal standards.

> Automated tools like `TRAMP` in `emacs` will drive a remote `ssh` session, but
> they must be able to extract the prompt which must start on a newline. It
> cannot contain colorized characters. Your `profile` and `rc` scripts must
> distinguish between non/interactive and non/login shells.

### Issues

#### SCP

When transferring from `windows -> windows`, GitBash `scp` didn't work -- `which scp` shows a path to `/usr/bin/scp`. This file is much older. It's a different executable and unprepared to handle Windows paths. When connecting to a remote host -- here `localhost` -- this `/usr/bin/scp` is unprepared to drive the Windows shell it's connecting to. 

> The additional layers of abstraction/translation are the primary reason that I
> don't want to invest in learning to develop from Windows. On one hand, the
> setup is simpler, but `Windows -> VM/Docker Linux` is actually more
> complicated than `MacOS -> VM/Docker Linux`, where Apple Silicon was the main
> hurdle there.

#### File Paths

##### TODO: test pushing/fetching files from linux

I'm also somewhat worried that unless the windows `ssh` shell gets set up properly, I won't be able to fetch/fush files from `linux` hosts. The initial terminal program to run gets configured somewhere in `sshd_config` IIRC, but there's likely an option in *each* program **and** in each program's configuration. That's *complicated*
