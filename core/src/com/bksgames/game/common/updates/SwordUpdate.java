package com.bksgames.game.common.updates;

import com.bksgames.game.common.utils.Direction;

public record SwordUpdate(Direction direction, int relativeX, int relativeY) implements Update {
    @Override
    public void visit(UpdateVisitor updateVisitor) {
        updateVisitor.visit(this);
    }
}
