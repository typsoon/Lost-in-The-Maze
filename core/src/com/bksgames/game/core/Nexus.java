package com.bksgames.game.core;

public class Nexus implements Tile, Vulnerable, KnownPosition{

    @Override
    public boolean isHollow() { return false;}

    private final int x,y;
    int hitPoints;

    public final Player owner;

    @Override
    public int getX() {return x;}

    @Override
    public int getY() {return y;}

    public Player getOwner() { return owner;}

    @Override
    public int getHitPoints() {
        return hitPoints;
    }

    //    TODO: write this
    @Override
    public void damage(SourceOfDamage sourceOfDamage) {

    }

    public Nexus(Player owner, int x, int y, Parameters parameters) {
        this.x = x;
        this.y = y;
        this.owner = owner;

        hitPoints = parameters.nexusHitPoints;
    }
}
