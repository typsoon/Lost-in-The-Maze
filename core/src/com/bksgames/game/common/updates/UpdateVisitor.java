package com.bksgames.game.common.updates;

public interface UpdateVisitor {
    void visit(EntityStateUpdate entityStateUpdate);
    void visit(EntityUpdate entityUpdate);
    void visit(LaserUpdate laserUpdate);
    void visit(SwordUpdate swordUpdate);
    void visit(TileUpdate tileUpdate);
}
