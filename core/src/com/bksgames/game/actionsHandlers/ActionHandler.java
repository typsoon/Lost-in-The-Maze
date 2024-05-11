package com.bksgames.game.actionsHandlers;

import com.bksgames.game.core.GameManager;
import com.bksgames.game.core.Move;
import com.bksgames.game.core.boards.Board;

public abstract class ActionHandler {
    Board board;
    public abstract void handle(Move action);
    ActionHandler(Board board) {
        this.board = board;
    }
}
