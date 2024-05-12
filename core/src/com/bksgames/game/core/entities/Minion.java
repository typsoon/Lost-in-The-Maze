package com.bksgames.game.core.entities;

import com.bksgames.game.core.utils.Owned;
import com.bksgames.game.core.utils.SourceOfDamage;
import com.bksgames.game.enums.Displayable;
import com.bksgames.game.enums.PlayerColor;

public class Minion implements Entity, Owned {
    int x, y, hitPoints,cooldown;
    PlayerColor owner;
    public boolean onCooldown(){return cooldown > 0;}

    public void nextTurn(){cooldown--;}
    @Override
    public void damage(SourceOfDamage sourceOfDamage) {

    }
    @Override
    public int getX() { return x;}

    @Override
    public int getY() { return y;}

    @Override
    public int getHitPoints() { return hitPoints;}

    @Override
    public void spawn(int x, int y, int hitPoints) {
        this.x = x;
        this.y = y;

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
    public Minion(int x, int y, int hitPoints, PlayerColor player) {
        spawn(x,y, hitPoints);
        this.owner = player;
    }


}
