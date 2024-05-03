package com.bksgames.game.core;

public abstract class Entity implements Vulnerable, KnownPosition{
    public int x,y;
    public int hitPoints;

    void spawn(int x, int y, Parameters parameters) {
        this.x = x;
        this.y = y;
    }
}
