package com.bksgames.game.core.actionsHandlers;

import com.bksgames.game.core.GameManager;
import com.bksgames.game.core.Move;
import com.bksgames.game.core.boards.Board;
import com.bksgames.game.enums.MoveTypes;

public class LaserHandler extends ActionHandler{
    @Override
    public void handle(Move action) {
        if(action.type() != MoveTypes.MOVE)
            throw new IllegalStateException("Wrong move type!");
    }
    LaserHandler(GameManager manager) {super(manager);}
}
