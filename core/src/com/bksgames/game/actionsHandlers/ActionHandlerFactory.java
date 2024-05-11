package com.bksgames.game.actionsHandlers;

import com.bksgames.game.core.GameManager;
import com.bksgames.game.enums.MoveTypes;

public class ActionHandlerFactory {
    public static ActionHandler CreateActionHandler(MoveTypes type, GameManager gameManager)
    {
        switch (type)
        {
            case DOOR -> {return new DoorHandler(gameManager);}
            case MOVE -> {return new MoveHandler(gameManager);}
            case LASER -> {return new LaserHandler(gameManager);}
            case SWORD -> {return new SwordHandler(gameManager);}
            case MIRROR -> {return new MirrorHandler(gameManager);}
            default -> {return null;}
        }
    }
}
