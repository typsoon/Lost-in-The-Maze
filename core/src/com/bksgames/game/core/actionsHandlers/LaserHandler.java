package com.bksgames.game.core.actionsHandlers;

import com.bksgames.game.core.GameManager;
import com.bksgames.game.core.Move;
import com.bksgames.game.core.boards.Board;
import com.bksgames.game.enums.Direction;
import com.bksgames.game.enums.MoveTypes;

import java.awt.*;

public class LaserHandler extends ActionHandler{
    @Override
    public void handle(Move action) {
        if(action.type() != MoveTypes.MOVE)
            throw new IllegalStateException("Wrong move type!");

        Point point = Direction.getNext(new Point(action.x(), action.y()), action.direction());

        while(board.getTile(point.x, point.y).isHollow()){
            Direction.getNext(point, action.direction());
        }
        // here POINT will point to the first non-tunnel tile
        // TODO: Implement cases when mirror or minion
    }
    LaserHandler(GameManager manager) {super(manager);}
}
