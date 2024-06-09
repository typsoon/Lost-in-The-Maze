package com.bksgames.game.core.main.actionsHandlers;

import com.bksgames.game.core.main.GameManager;
import com.bksgames.game.common.moves.ActionToken;

public class ActionHandlerFactory {
    public static ActionHandler CreateActionHandler(ActionToken type, GameManager manager)
    {
      return switch (type)
        {
            case DOOR -> new DoorHandler(manager);
            case MOVE -> new MoveHandler(manager);
            case LASER -> new LaserHandler(manager);
            case SWORD -> new SwordHandler(manager);
            case MIRROR -> new MirrorHandler(manager);
        };
    }
}
