package com.bksgames.game.core.tiles;

import com.bksgames.game.core.main.updateHolders.UpdateHolder;
import com.bksgames.game.core.utils.*;
import com.bksgames.game.common.Displayable;
import com.bksgames.game.common.PlayerColor;

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
    public Point getRespawnPosition() {
        return null;
    }
    @Override
    public int getHitPoints() {
        return hitPoints;
    }
    //    TODO: write this
    @Override
    public UpdateHolder damage(SourceOfDamage sourceOfDamage) {
        hitPoints-=sourceOfDamage.getDamageValue();
        if(hitPoints <= 0)
            return null; //TODO nexus destroyed update
        return null;
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
        return PlayerEnums.getNexusDisplayable(owner);
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
