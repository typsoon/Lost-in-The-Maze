package com.bksgames.game.common.updates;

public interface Update {
    void visit(UpdateVisitor updateVisitor);
//    pass relative coordinates
    int relativeX();
    int relativeY();
}
