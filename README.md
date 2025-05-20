[![Discord](https://img.shields.io/discord/853331530004299807?color=7289da&label=discord&logo=discord&logoColor=white)](https://discord.gg/M2SvqCu4e9)

[Download](https://github.com/kejonaMC/floodgate-skript/releases)

Floodgate support for Skript. Built with SkriptLang's fork, version 2.6.3. 

Usage:

Testing if a Player is from Floodgate:

```
if player is from floodgate:
  send "Hello there!"
```

Finding a player's platform:

```
set {_device} to device of floodgate player
```

Finding a player's version:

```
set {_version} to version of floodgate player
```

Finding a player's language/locale:

```
set {_language} to language of floodgate player
```

*Compiling:* Clone the repo and run `mvn clean install` in the source directory.
