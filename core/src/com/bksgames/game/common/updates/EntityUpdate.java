package com.bksgames.game.common.updates;

import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.common.Displayable;

public record EntityUpdate(Direction direction, Displayable displayable, int relativeX, int relativeY) implements Update {
    @Override
    public void visit(UpdateVisitor updateVisitor) {
        updateVisitor.visit(this);
    }

//    default boolean isStaticUpdate() {return getMoveType() == null && getMoveType() == null;}
}
