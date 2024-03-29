## Maze Runner ruleset

---

### Goal of the game

There will be $2$ teams both consisting of $3$ minions and a _nexus_. The players will try to navigate the maze and capture the enemy _nexus_ by reaching it with one of their minions.

### Fog of war

The active player is the player who is currently taking their turn.

The layout of the maze will not be revealed to the players from the start. They have to create the map of it by moving their minions. The files of the maze have $3$ variants of visibility 
<!-- check the grammar above -->

* Segments of maze which have not yet been revealed are covered in _fog of war_.

* Segments that have been discovered but are not seen by any of the minions active player controls will be marked as _revealed_

* Segments that are seen by any of the minions active player controls will be marked as _visible_.

Fog of war dissapears whenever a file covered in it moves into the field of view of a minion active player controls. The file is then marked as _discovered_.
<!-- TODO: write when does the fog of war disappear -->

### Field of view

The minions see a $3 \times 3$ square around them and also they see in straight lines. If there is a mirror that is not separated by walls from a minion then that minion sees everything that is reflected in this mirror. This effect is stackable (that means that if a mirror $1$ is reflected in mirror $2$ then the minion watching mirror $2$ sees everything that is reflected in the mirror $1$) and a minion can bend his vision in one direction using up to $5$ mirrors.

### Mirrors

