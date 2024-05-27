package com.bksgames.game.common.updates;

import com.bksgames.game.common.Displayable;
import com.bksgames.game.common.EntityEvent;
import com.bksgames.game.common.moves.ActionToken;

public interface EntityStateUpdate extends Update {
    boolean isMinion();
    EntityEvent entityEventType();
    ActionToken getMoveType();
}
