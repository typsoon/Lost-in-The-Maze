package com.bksgames.game.common.updates;

import com.bksgames.game.common.Displayable;

public record TileUpdate(Displayable whatToDisplay, boolean isVisible, int relativeX, int relativeY) implements   Update {
    @Override
    public void visit(UpdateVisitor updateVisitor) {
        updateVisitor.visit(this);
    }
}
