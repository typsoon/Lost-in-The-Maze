package com.bksgames.game.core.main.actionsHandlers;

import com.bksgames.game.core.main.GameManager;
import com.bksgames.game.globalClasses.enums.MoveTypes;

public class ActionHandlerFactory {
    public static ActionHandler CreateActionHandler(MoveTypes type, GameManager manager)
    {
      return switch (type)
        {
            case DOOR -> new DoorHandler(manager);
            case MOVE -> new MoveHandler(manager);
            case LASER -> new LaserHandler(manager);
            case SWORD -> new SwordHandler(manager);
            case MIRROR -> new MirrorHandler(manager);
            default -> throw new RuntimeException("Missing ActionHandler for " + type.name() + "!");
        };
    }
}
