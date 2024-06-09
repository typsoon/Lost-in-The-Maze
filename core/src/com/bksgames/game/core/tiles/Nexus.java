package com.bksgames.game.core.tiles;

import com.bksgames.game.common.Displayable;
import com.bksgames.game.common.PlayerColor;
import com.bksgames.game.core.main.updateHolders.UpdateHolder;
import com.bksgames.game.core.utils.*;

import java.util.Collection;
import java.util.List;

/**
 * Representing {@code Nexus}
 *
 * @author typsoon
 * @author riper
 */
public class Nexus implements Vulnerable, KnownPosition, Owned, Tile {

    private int hitPoints;
    private final PlayerColor owner;
    private final Point position;

    @Override
    public Collection<Vulnerable> getVulnerable() {
        return List.of(this);
    }

    //Vulnerable
    @Override
    public int getHitPoints() {
        return hitPoints;
    }

    @Override
    public UpdateHolder<?> damage(SourceOfDamage sourceOfDamage) {
        hitPoints -= sourceOfDamage.getDamageValue();
        if (hitPoints <= 0)
            return null; //TODO nexus destroyed update
        return null;
    }

    //KnownPosition
    @Override
    public Point getPosition() {
        return position.copy();
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
    public Nexus(PlayerColor owner, Point position, int hitPoints) {
        this.position = position.copy();
        this.owner = owner;
        this.hitPoints = hitPoints;
    }


}
