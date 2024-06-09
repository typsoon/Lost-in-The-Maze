package com.bksgames.game.common.moves;

import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.utils.Direction;


public abstract class Move {
    protected final IncompleteMove incompleteMove;
    protected final Point minionPosition;

    protected abstract void handle();

    public Move(IncompleteMove incompleteMove, Point minionPosition) {
        this.incompleteMove = incompleteMove;
        this.minionPosition = minionPosition;
    }
}

