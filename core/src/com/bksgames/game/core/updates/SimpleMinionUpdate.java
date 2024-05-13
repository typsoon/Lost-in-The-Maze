package com.bksgames.game.core.updates;

import com.bksgames.game.globalClasses.MinionUpdate;
import com.bksgames.game.globalClasses.enums.*;

public class SimpleMinionUpdate implements MinionUpdate {
    Direction direction;
    Displayable displayable;
    MinionEvent event;
    MoveTypes move;
    int x;
    int y;
    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public Displayable getDisplayable() {
        return displayable;
    }

    @Override
    public MoveTypes getMoveType() {
        return move;
    }

    @Override
    public MinionEvent getEvent() {
        return event;
    }

    @Override
    public UpdateIDs getID() {
        return UpdateIDs.MINION_UPDATE;
    }

    @Override
    public int getRelativeX() {
        return x;
    }

    @Override
    public int getRelativeY() {
        return y;
    }
    public SimpleMinionUpdate(Direction direction, Displayable displayable, MinionEvent event, MoveTypes move, int x, int y){
        this.direction = direction;
        this.displayable = displayable;
        this.event = event;
        this.move = move;
        this.x = x;
        this.y = y;
    }
}
