package com.bksgames.game.common.updates;

public interface UpdateVisitor {
    default void visit(Update update) {throw new IllegalStateException("this update doesnt implement any specific update kind");}

    void visit(EntityStateUpdate entityStateUpdate);
    void visit(EntityUpdate entityUpdate);
    void visit(LaserUpdate laserUpdate);
    void visit(SwordUpdate swordUpdate);
    void visit(TileUpdate tileUpdate);
}
