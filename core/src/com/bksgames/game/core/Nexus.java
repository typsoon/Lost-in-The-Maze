package com.bksgames.game.core;

public class Nexus implements Tile, Vulnerable{

    @Override
    public boolean isHollow() { return false;}

    private final int x,y;
    int hitPoints;

    public final Player owner;

    public int getX() {return x;}
    public int getY() {return y;}
    public Player getOwner() { return owner;}

    public Nexus(Player owner, int x, int y, Parameters parameters) {
        this.x = x;
        this.y = y;
        this.owner = owner;

        hitPoints = parameters.nexusHitPoints;
    }

    @Override
    public int getHitPoints() {
        return hitPoints;
    }

//    TODO: write this
    @Override
    public void damage(SourceOfDamage sourceOfDamage) {

    }
}
