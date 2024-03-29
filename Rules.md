## Maze Runner ruleset

---

### Goal of the game

There will be $2$ teams both consisting of $3$ minions and a _nexus_. The players will try to navigate the maze and capture the enemy _nexus_ by reaching it with one of their minions.

### Fog of war

The active player is the player who is currently taking their turn.

The layout of the maze will not be revealed to the players from the start. They have to create the map of it by moving their minions. The files of the maze have $3$ variants of visibility 

* Segments of maze which have not yet been revealed are covered in _fog of war_.

* Segments that have been discovered but are not seen by any of the minions active player controls will be marked as _discovered_

* Segments that are seen by any of the minions active player controls will be marked as _visible_.