package com.bksgames.game.core.test.actionsHandlers;

import com.bksgames.game.core.test.main.GameManager;
import com.bksgames.game.globalClasses.enums.MoveTypes;

public class ActionHandlerFactory {
    public static ActionHandler CreateActionHandler(MoveTypes type, GameManager manager)
    {
        switch (type)
        {
            case DOOR -> {return new DoorHandler(manager);}
            case MOVE -> {return new MoveHandler(manager);}
            case LASER -> {return new LaserHandler(manager);}
            case SWORD -> {return new SwordHandler(manager);}
            case MIRROR -> {return new MirrorHandler(manager);}
            default -> {return null;}
        }
    }
}
