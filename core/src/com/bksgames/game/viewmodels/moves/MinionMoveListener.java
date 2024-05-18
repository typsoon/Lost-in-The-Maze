package com.bksgames.game.viewmodels.moves;


import com.bksgames.game.core.utils.Point;

public interface MinionMoveListener {
    void makeMove(IncompleteMove incompleteMove);

//  TODO: Add additional interface to hide setLocation() method
    void setLocation(Point minionLocation);
}
