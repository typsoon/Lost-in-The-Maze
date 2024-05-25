package com.bksgames.game.common.updates;

import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.common.Displayable;
import com.bksgames.game.common.MinionEvent;
import com.bksgames.game.common.moves.ActionToken;

public interface MinionUpdate extends Update {
    Direction getDirection();
    Displayable getDisplayable();

    ActionToken getMoveType();
    MinionEvent getEvent();

    default boolean isStaticUpdate() {return getMoveType() == null && getEvent() == null;}
}
