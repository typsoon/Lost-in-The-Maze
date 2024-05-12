package com.bksgames.game.core;

public class Minion implements Entity{
    int x, y, hitPoints,cooldown;

    public boolean onCooldown(){return cooldown > 0;}

    public void nextTurn(){cooldown--;}
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
    public void spawn(int x, int y, int hitPoints) {
        this.x = x;
        this.y = y;

        this.hitPoints = hitPoints;
    }

    public Minion(int x, int y, int hitPoints) {spawn(x,y, hitPoints);}
}
