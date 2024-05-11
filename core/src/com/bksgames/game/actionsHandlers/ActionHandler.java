package com.bksgames.game.actionsHandlers;

import com.bksgames.game.core.GameManager;
import com.bksgames.game.core.Move;

public abstract class ActionHandler {
    GameManager gm;
    public abstract void handle(Move action);
    ActionHandler(GameManager gm) {
        this.gm = gm;
    }
}
