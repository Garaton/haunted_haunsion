# haunted_haunsion
Author : Garett Rogers

### Overview
This is a old High School final project terminal game, which I yoinked stuff
from a personal terminal game project to make this.
RPG which is a parody of spooky games, also has stanley parable references and
direct inspiration. There are multiple endings, and there is a secret gamemode.

### Classes
#### Main.java
    Handles the main game loop, location events, and uses Game.java methods

#### Game.java
    Has game method which handles movement and combat.
    Combat method has 3 different combat styles as dictated by enemy PIV

#### Player.java
    Contains player information (level, inventory etc), some PIVs are unused
    Handles leveling up, inventory management, etc.

#### Enemy.java
    Contains enemy info like hp, atk, and combat style. Mainly just to hold
    information for the Game.java combat method.