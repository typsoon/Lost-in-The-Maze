package com.bksgames.game.globalClasses;

import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.globalClasses.enums.Displayable;
import com.bksgames.game.globalClasses.enums.MinionEvent;
import com.bksgames.game.globalClasses.enums.MoveTypes;

public interface MinionUpdate extends Update {
    Direction getDirection();
    Displayable getDisplayable();

    MoveTypes getMoveType();
    MinionEvent getEvent();

    default boolean isStaticUpdate() {return getMoveType() == null && getEvent() == null;}
}
