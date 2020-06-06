Floodgate support for Skript. Built with SkriptLang's fork, version 2.5alpha3. 

Usage:

Testing if a Player is from Floodgate:

```
if player is from floodgate:
  send "Hello there!"
```

Finding a player's platform:

```
set {_device} to device of player
```

Getting a player's UUID:

```
set {_uuid} to uuid of floodgate player
```

*Compiling:* Clone the repo and run `mvn clean install` in the source directory.
