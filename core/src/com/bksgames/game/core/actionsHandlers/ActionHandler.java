package com.bksgames.game.core.actionsHandlers;

import com.bksgames.game.core.GameManager;
import com.bksgames.game.core.Move;
import com.bksgames.game.core.boards.Board;

public abstract class ActionHandler {
    GameManager gameManager;
    public abstract void handle(Move action);
    ActionHandler(GameManager manager) {
        this.gameManager = manager;
    }
}
