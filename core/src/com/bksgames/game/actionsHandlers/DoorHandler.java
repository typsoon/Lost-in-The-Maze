package com.bksgames.game.actionsHandlers;

import com.bksgames.game.core.GameManager;
import com.bksgames.game.core.Move;
import com.bksgames.game.enums.MoveTypes;

public class DoorHandler extends ActionHandler {
    @Override
    public void handle(Move action) {
        if(action.type() != MoveTypes.MOVE)
            throw new IllegalStateException("Wrong move type!");
    }
    DoorHandler(GameManager gm) {
        super(gm);
    }
}
