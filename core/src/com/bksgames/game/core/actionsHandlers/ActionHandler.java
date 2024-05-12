package com.bksgames.game.core.actionsHandlers;

import com.bksgames.game.core.main.GameManager;
import com.bksgames.game.globalClasses.Move;

public abstract class ActionHandler {
    GameManager gameManager;
    public abstract void handle(Move action);
    ActionHandler(GameManager manager) {
        this.gameManager = manager;
    }
}
