package com.bksgames.game.actionsHandlers;

import com.bksgames.game.core.GameManager;
import com.bksgames.game.core.boards.Board;
import com.bksgames.game.enums.MoveTypes;

public class ActionHandlerFactory {
    public static ActionHandler CreateActionHandler(MoveTypes type, Board board)
    {
        switch (type)
        {
            case DOOR -> {return new DoorHandler(board);}
            case MOVE -> {return new MoveHandler(board);}
            case LASER -> {return new LaserHandler(board);}
            case SWORD -> {return new SwordHandler(board);}
            case MIRROR -> {return new MirrorHandler(board);}
            default -> {return null;}
        }
    }
}
