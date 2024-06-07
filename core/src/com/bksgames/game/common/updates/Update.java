package com.bksgames.game.common.updates;

public interface Update {
    UpdateIDs getID();

    default void visit(UpdateVisitor updateVisitor) {updateVisitor.visit(this);}
//    pass relative coordinates
    int getRelativeX();
    int getRelativeY();
}
