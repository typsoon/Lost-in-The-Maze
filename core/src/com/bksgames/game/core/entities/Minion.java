package com.bksgames.game.core.entities;

import com.bksgames.game.common.Displayable;
import com.bksgames.game.common.EntityEvent;
import com.bksgames.game.common.PlayerColor;
import com.bksgames.game.common.moves.ActionToken;
import com.bksgames.game.common.updates.EntityStateUpdate;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.core.main.updateHolders.UpdateHolder;
import com.bksgames.game.core.main.updateHolders.UpdateHolderFactory;
import com.bksgames.game.core.updates.SimpleEntityStateUpdate;
import com.bksgames.game.core.utils.*;

import java.util.EnumMap;

/**
 * Representing {@code Minion}
 *
 * @author typsoon
 * @author riper
 * @author jajko
 */
public class Minion implements Entity, Owned, Interactive, Respawnable {
    private Point position;
    private final PlayerColor owner;

    private final Point respawnPosition;

    private final int startingHP;
    private int hitPoints;

    private final int startingAP;
    private int actionPoints;

    private final EnumMap<ActionToken, Integer> actionCosts;


    public void nextTurn() {
        actionPoints = startingAP;
    }

    /**
     * Move {@code Minion} to adjacent field
     *
     * @param direction {@code Direction} of moving
     */
    public void moveMinion(Direction direction) {
        direction.next(position);
    }

    //Entity
    @Override
    public void spawn(Point position) {
        this.position = position.getPosition();
        this.hitPoints = startingHP;
        this.actionPoints = startingAP;
    }

    @Override
    public Point getBaseSpawnPosition() {
        return respawnPosition;
    }

    @Override
    public int getHitPoints() {
        return hitPoints;
    }

    @Override
    public UpdateHolder<EntityStateUpdate> damage(SourceOfDamage sourceOfDamage) {
        hitPoints -= sourceOfDamage.getDamageValue();
        if (hitPoints <= 0) {
            return UpdateHolderFactory.produceUpdateHolder(
                    new SimpleEntityStateUpdate(true,EntityEvent.KILLED,null,position)
            );
        }
        return null;
    }

    @Override
    public Point getPosition() {
        return position.getPosition();
    }

    @Override
    public Displayable getDisplayable() {
        return PlayerEnums.getMinionDisplayable(owner);
    }

    //Owned
    @Override
    public PlayerColor getOwner() {
        return owner;
    }

    //Interactive
    @Override
    public int getActionPoints() {
        return actionPoints;
    }

    @Override
    public boolean makeAction(ActionToken actionToken) {
        if (!canMakeAction(actionToken)) {
            return false;
        }
        actionPoints -= actionCosts.get(actionToken);
        return true;
    }

    @Override
    public boolean canMakeAction(ActionToken actionToken) {
        return actionPoints >= actionCosts.get(actionToken);
    }

    /**
     * Constructs {@code Minion}
     *
     * @param position   initial position
     * @param startingHP initial HP
     * @param player     owner
     */
    public Minion(Point position, int startingHP, int startingAP, PlayerColor player) {
        this.owner = player;
        this.startingAP = startingAP;
        this.startingHP = startingHP;
        this.respawnPosition = position;
        actionCosts = new EnumMap<>(ActionToken.class);
        actionCosts.put(ActionToken.MOVE, 1);
        actionCosts.put(ActionToken.SWORD, 2);
        actionCosts.put(ActionToken.LASER, 3);
        actionCosts.put(ActionToken.MIRROR, 4);
        spawn(position);
    }


}
