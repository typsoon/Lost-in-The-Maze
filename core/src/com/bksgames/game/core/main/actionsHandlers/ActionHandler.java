package com.bksgames.game.core.main.actionsHandlers;

import com.bksgames.game.core.main.GameManager;
import com.bksgames.game.common.moves.Move;

/**
 * Class handling one type({@code Move}) of action
 * @author riper
 *
 */
public abstract class ActionHandler {
    /**
     * owner of handler
     */
    GameManager gameManager;

    /**
     * handling an action
     * @param action - action to handle
     */
    public abstract void handle(Move action);

    /**
     * Constructs an {@code ActionHandler}
     */
    ActionHandler(GameManager manager) {
        this.gameManager = manager;
    }
}
