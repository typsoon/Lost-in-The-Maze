package com.bksgames.game.common;

import com.bksgames.game.common.enums.Direction;
import com.bksgames.game.common.enums.Displayable;
import com.bksgames.game.common.enums.MinionEvent;
import com.bksgames.game.common.enums.ActionToken;

public interface MinionUpdate extends Update {
    Direction getDirection();
    Displayable getDisplayable();

    ActionToken getMoveType();
    MinionEvent getEvent();

    default boolean isStaticUpdate() {return getMoveType() == null && getEvent() == null;}
}
