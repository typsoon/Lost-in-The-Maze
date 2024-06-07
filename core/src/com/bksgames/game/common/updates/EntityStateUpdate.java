package com.bksgames.game.common.updates;

import com.bksgames.game.common.EntityEvent;
import com.bksgames.game.common.moves.ActionToken;

public record EntityStateUpdate(boolean isMinion, EntityEvent entityEventType, ActionToken getMoveType, int relativeX, int relativeY) implements Update {
    @Override
    public void visit(UpdateVisitor updateVisitor) {
        updateVisitor.visit(this);
    }
}
