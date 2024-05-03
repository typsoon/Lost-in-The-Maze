package com.bksgames.game.core;

public class Minion implements Entity{
    int x, y, hitPoints;

    @Override
    public void damage(SourceOfDamage sourceOfDamage) {

    }

    @Override
    public int getX() { return x;}

    @Override
    public int getY() { return y;}

    @Override
    public int getHitPoints() { return hitPoints;}

    @Override
    public void spawn(int x, int y, Parameters parameters) {
        this.x = x;
        this.y = y;

        hitPoints = parameters.minionHitPoints;
    }

    public Minion(int x, int y, Parameters parameters) {spawn(x,y, parameters);}
}
