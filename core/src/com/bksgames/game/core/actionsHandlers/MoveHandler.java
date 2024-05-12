package com.bksgames.game.core.actionsHandlers;

import com.bksgames.game.core.main.GameManager;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.globalClasses.enums.MoveTypes;

public class MoveHandler extends ActionHandler {
    @Override
    public void handle(Move action) {
        if(action.type() != MoveTypes.MOVE)
            throw new IllegalStateException("Wrong move type!");
    }
    MoveHandler(GameManager manager) {
        super(manager);
    }
}
