package com.bksgames.game.core.test.actionsHandlers;

import com.bksgames.game.core.test.main.GameManager;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.globalClasses.enums.MoveTypes;

public class DoorHandler extends ActionHandler {
    @Override
    public void handle(Move action) {
        if(action.type() != MoveTypes.MOVE)
            throw new IllegalStateException("Wrong move type!");
    }
    DoorHandler(GameManager manager) {
        super(manager);
    }
}
