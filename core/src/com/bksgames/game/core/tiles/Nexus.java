package com.bksgames.game.core.tiles;

import com.bksgames.game.core.KnownPosition;
import com.bksgames.game.core.SourceOfDamage;
import com.bksgames.game.core.Vulnerable;
import com.bksgames.game.enums.Displayable;
import com.bksgames.game.enums.PlayerColor;

public class Nexus implements Tile, Vulnerable, KnownPosition {
    private final int x,y;
    int hitPoints;
    public final PlayerColor owner;

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

    @Override
    public Displayable getDisplayable() {
        if(getOwner()==PlayerColor.BLUE)
            return Displayable.BLUE_NEXUS;
        else
            return Displayable.RED_NEXUS;
    }

    @Override
    public Tunnel getTunnel() {
        return null;
    }
}
