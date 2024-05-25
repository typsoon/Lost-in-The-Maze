package com.bksgames.game.core.main.actionsHandlers;

import com.bksgames.game.core.main.GameManager;
import com.bksgames.game.common.Move;
import com.bksgames.game.common.enums.ActionToken;

/**
 * {@code ActionHandler} for {@code MoveTypes.DOOR}
 * @author riper
 */
public class DoorHandler extends ActionHandler {
    @Override
    public void handle(Move action) {
        if(action.type() != ActionToken.DOOR)
            throw new IllegalStateException("Wrong move type!");
    }
    DoorHandler(GameManager manager) {
        super(manager);
    }
}
