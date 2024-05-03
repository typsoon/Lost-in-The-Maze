package com.bksgames.game.core;

public abstract class Entity {
    public int x,y;
    public int hitPoints;

    void spawn(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
