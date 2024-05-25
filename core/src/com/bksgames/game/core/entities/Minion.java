package com.bksgames.game.core.entities;

import com.bksgames.game.core.utils.Owned;
import com.bksgames.game.core.utils.PlayerEnums;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.core.utils.SourceOfDamage;
import com.bksgames.game.common.enums.Direction;
import com.bksgames.game.common.enums.Displayable;
import com.bksgames.game.common.enums.PlayerColor;

/**
 * Representing {@code Minion}
 * @author typsoon
 * @author riper
 * @author jajko
 */
public class Minion implements Entity, Owned{
    private Point position;
    private int hitPoints;
    private int actionPoints;

    private final PlayerColor owner;
    private final int startingAP;

    public void nextTurn(){
        actionPoints = startingAP;
    }
    /**
     * Move {@code Minion} to adjacent field
     * @param direction {@code Direction} of moving
     */
    public void moveMinion(Direction direction){
        direction.next(position);
    }

    //Entity
    @Override
    public void spawn(Point position, int hitPoints) {
        this.position = position.getPosition();
        this.hitPoints = hitPoints;
    }
    @Override
    public int getHitPoints() { return hitPoints;}
    @Override
    public boolean damage(SourceOfDamage sourceOfDamage) {
        hitPoints-= sourceOfDamage.getDamageValue();
        return hitPoints <= 0;
    }
    @Override
    public Point getPosition(){
        return position.getPosition();
    }
    @Override
    public Displayable getDisplayable() {
        return PlayerEnums.getMinionColor(owner);
    }

    //Owned
    @Override
    public PlayerColor getOwner() {
        return owner;
    }

    /**
     *
     * Constructs {@code Minion}
     * @param position initial position
     * @param hitPoints initial HP
     * @param player owner
     */
    public Minion(Point position, int hitPoints, int startingAP, PlayerColor player) {
        spawn(position, hitPoints);
        this.owner = player;
        this.startingAP = startingAP;
    }
}
