package com.bksgames.game.viewmodels.moves;

import java.awt.*;

public interface MinionMoveListener {
    void makeMove(IncompleteMove incompleteMove);

//  TODO: Add additional transient interface to hide setLocation() method
    void setLocation(Point minionLocation);
}
