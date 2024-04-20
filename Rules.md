# Maze Runner ruleset

---

## General rules

### Goal of the game

There are two teams, each comprised of three minions and a nexus. The objective for each team is to navigate through the maze and reach the enemy nexus with one of their minions in order to capture it.

### Fog of war

The active player is the one who is currently taking their turn.

The layout of the maze will not be revealed to the players from the start. Instead, they must create its map by moving their minions. The maze files contain three variants of visibility.
<!-- check the grammar above -->
* Segments discovered but not within the sight of any player-controlled minion will be marked as _revealed_.

* Segments of maze which have not yet been revealed are covered in _fog of war_

* Segments seen by any minion under the control of an active player will be marked as _visible_.

Fog of war disappears whenever a file covered in it moves into the field of view of a minion active player controls. The file is then marked as _revealed_.
<!-- TODO: write when does the fog of war disappear -->

### Field of view

#### When not wielding a lantern

The minions can perceive $8$ adjacent files. 


#### When wielding a lantern
When wielding a lantern minions perceive straight lines extending from their position. If a mirror is within the line of sight of a minion, without obstruction by walls, the minion will see everything reflected in that mirror. This effect can compound: if Mirror $1$ is reflected in Mirror $2$, then the minion observing Mirror $2$ will perceive everything reflected in Mirror $1$ as well. A minion can adjust their line of sight in one direction by utilizing up to a maximum of [maxMirrorsBending](./Game%20Parameters.md) mirrors.

### The course of the turn

#### _beginning of turn_

Each turn initiates with the _Beginning of Turn_ phase, during which players are unable to take any actions. Instead, they observe the events that unfolded in visible files during their opponent's turn. Minions under the active player's control receive a set number of actions, defined by [actionsNumber](./Game%20Parameters.md) parameter.

<!-- Maybe in some situations state of _revealed_ files could be observed. For example destroying a door -->

#### _turn_

Following the initial phase, players progress to the main segment of their turn. Here, they are granted the opportunity to utilize the actions of their minions strategically.

#### _end of turn_

As the turn draws to a close, any actions left unused by minions are lost.

<!-- Unless... -->

## Objects

### Nexus

_Nexus_ is the most important object in the game. Players must safeguard their own _nexus_ while looking for a way to gain control of their enemy's _nexus_ to secure victory.
<!-- Insert the structure that spawns alongside the nexus -->

### Mirrors

Mirrors, unlike other structures, do not block the movement of minions. They can be put down as long as the building minion has not used any actions during the current turn. ...

<!-- Write more about the mirrors -->

### Walls 
Walls serve as barriers, obstructing both the passage and vision of minions. Players have a limited number of opportunities, defined by the parameter [wallBuildCount](./Game%20Parameters.md), to put down walls. Moreover, walls can only be built if the building minion has not yet used any actions during the current turn.

<!-- In which file is the wall placed upon building it? -->

### Doors

Minions possess the ability to construct doors by spending [doorPrice](./Game%20Parameters.md) actions. After putting down a door, a minion must wait for a duration equal to [doorCooldown](Game%20Parameters.md) turns before constructing another. Each door has a set amount of hitpoints, defined by [doorHitpoints](./Game%20Parameters.md) parameter. Doors block the vision of minions. A door can't be placed if there is an another door of the same colour in a 5 $\times$5 square with the center in the place where the door is being built.

<!-- Maybe the number of doors that can be built should be limited -->

## The Minions

Minions have [minionsHitpoints](./Game%20Parameters.md) hitpoints. Upon dying they have to wait [turnsToRespawn](./Game%20Parameters.md) turns before respawning next to their controller nexus 

Minions can use their actions in several ways:

#### Moving

moving one file horizontally or diagonally costs one action.

#### Attacking

- switching from lantern to sword costs one action.

- attacking with the sword costs one action. Sword deals one damage.

- Establishing a laser incurs a cost of [actionsNumber](./Game%20Parameters.md) actions. The laser inflicts [laserDamage](./Game%20Parameters.md) damage. It activates during the opponent's beginning-of-turn phase and ricochets off mirrors. Its trajectory halts upon reaching a wall.
 
#### Building

- Building walls or mirrors costs [actionsNumber](./Game%20Parameters.md) actions.

- Building doors costs [doorPrice](./Game%20Parameters.md) actions.


## Golems, towers and extra nexuses

Coming soon