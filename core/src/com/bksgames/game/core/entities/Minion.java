package com.bksgames.game.core.entities;

import com.bksgames.game.core.utils.Owned;
import com.bksgames.game.core.utils.SourceOfDamage;
import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.globalClasses.enums.Displayable;
import com.bksgames.game.globalClasses.enums.PlayerColor;

import java.awt.*;

public class Minion implements Entity, Owned {
    Point position;
    int hitPoints, cooldown;
    PlayerColor owner;
    public boolean onCooldown(){return cooldown > 0;}

    public void nextTurn(){cooldown--;}

    public void moveMinion(Direction direction){
        Direction.next(position, direction);
    }

    @Override
    public void damage(SourceOfDamage sourceOfDamage) {

    }
    @Override
    public int getX() { return position.x;}

    @Override
    public int getY() { return position.y;}

    @Override
    public int getHitPoints() { return hitPoints;}

    @Override
    public void spawn(Point position, int hitPoints) {
        this.position = new Point(position);
        this.hitPoints = hitPoints;
    }

    @Override
    public Displayable getDiplayable() {
        if(owner==PlayerColor.BLUE)
            return Displayable.BLUE_MINION;
        else
            return Displayable.RED_MINION;
    }

    @Override
    public PlayerColor getOwner() {
        return owner;
    }

    public Minion(Point position, int hitPoints, PlayerColor player) {
        spawn(position, hitPoints);
        this.owner = player;
    }
}
