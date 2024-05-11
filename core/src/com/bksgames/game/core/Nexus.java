package com.bksgames.game.core;

import com.bksgames.game.core.tiles.Tile;
import com.bksgames.game.enums.PlayerColor;

public class Nexus implements Tile, Vulnerable, KnownPosition{
    private final int x,y;
    int hitPoints;
    public final PlayerColor owner;

    @Override
    public boolean isHollow() { return false;}

    @Override
    public int getX() {return x;}

    @Override
    public int getY() {return y;}

    public PlayerColor getOwner() { return owner;}

    @Override
    public int getHitPoints() {
        return hitPoints;
    }

    //    TODO: write this
    @Override
    public void damage(SourceOfDamage sourceOfDamage) {
    }

    public Nexus(PlayerColor owner, int x, int y, int hitPoints) {
        this.x = x;
        this.y = y;
        this.owner = owner;

        this.hitPoints = hitPoints;
    }
}
