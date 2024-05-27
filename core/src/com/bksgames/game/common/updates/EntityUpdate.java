package com.bksgames.game.common.updates;

import com.bksgames.game.common.EntityEvent;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.common.Displayable;
import com.bksgames.game.common.moves.ActionToken;

public interface EntityUpdate extends Update {
    Direction getDirection();
    Displayable getDisplayable();

//    default boolean isStaticUpdate() {return getMoveType() == null && getMoveType() == null;}
}
