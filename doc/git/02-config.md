> NOTE these notes are early. Learning to write the Mermaid diagrams below may be helpful. They are generated using [Git Graph](https://mermaid.ai/open-source/syntax/gitgraph.html)

# Git Config

## `~/.gitconfig`

Git has a few different configuration locations where it *"merges"* options. Thus, you can have global configuration and then project-specific configurations. Below, the order in which these configurations are applied

- The system config, `$(prefix)/etc/gitconfig`, usually at `/etc/gitconfig`.
- The user's `$XDG_CONFIG_HOME/git/config` or, if that doesn't exist, `~/.gitconfig`. This file will exist somewhere else for Windows users. Thus, you may prefer using `git config --global` instead.
- `$GIT_DIR/config`

> `$GIT_DIR` refers to the `$MYPROJECT/.git` which contains the repository's local files.

A project's configuration, found at `$GIT_DIR/.git/config` will be set to some default values after you `git clone` a repository. And thus, you need to set them for each new clone.

### User Config

This section configures `git` to include

```gitconfig
[user]
  email = myemailforgithub@example.com
  name = Myname Ingitcommits

[github]
  user = dcunited001

# This section sets `autocrlf` to convert Window's `\r\n` line endings to `\n`
[core]
  autocrlf = input
```

### Configure `pull` and `rebase`

With [pull.rebase](https://git-scm.com/docs/git-config#Documentation/git-config.txt-pullrebase), `git pull` will rebase instead of merge, making remote changes easier to deal with. See the diagram at the top.

```gitconfig
[pull]
  rebase = true
```

With `rerere.enabled`, rebasing and pulling will remember merge conflict resolution, making them less likely to occur again. (TODO: explain further?)

```gitconfig
# https://git-scm.com/docs/git-config#Documentation/git-config.txt-rerereenabled
[rerere]
  enabled = true
```

With `rebase.autoStash`, you can pull without clearing out your index & worktree. (NOTE: explain further?)

```
# https://git-scm.com/docs/git-config#Documentation/git-config.txt-rebaseautoStash
[rebase]
  autoStash = true
```
