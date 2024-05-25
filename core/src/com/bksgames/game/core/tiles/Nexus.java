package com.bksgames.game.core.tiles;

import com.bksgames.game.core.utils.*;
import com.bksgames.game.common.enums.Displayable;
import com.bksgames.game.common.enums.PlayerColor;

/**
 * Representing {@code Nexus}
 * @author typsoon
 * @author riper
 */
public class Nexus implements  Vulnerable, KnownPosition, Owned, Tile {

    private int hitPoints;
    private final PlayerColor owner;
    private final Point position;

    //Vulnerable
    @Override
    public int getHitPoints() {
        return hitPoints;
    }
    //    TODO: write this
    @Override
    public boolean damage(SourceOfDamage sourceOfDamage) {
        hitPoints-=sourceOfDamage.getDamageValue();
        return hitPoints <= 0;
    }

    //KnownPosition
    @Override
    public Point getPosition() {
        return position.getPosition();
    }

    //Owned
    @Override
    public PlayerColor getOwner() {
        return owner;
    }

    //Tile
    @Override
    public Displayable getDisplayable() {
        return PlayerEnums.getNexusColor(owner);
    }

    /**
     * Creates Nexus
     */
    public Nexus(PlayerColor owner,Point position, int hitPoints) {
        this.position = position.getPosition();
        this.owner = owner;
        this.hitPoints = hitPoints;
    }
}
