package com.bksgames.game.core.updates;

import com.bksgames.game.common.updates.SwordUpdate;
import com.bksgames.game.common.updates.UpdateIDs;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.core.utils.Point;

public class SimpleSwordUpdate implements SwordUpdate {
    Direction direction;
    Point position;
    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public UpdateIDs getID() {
        return UpdateIDs.SWORD_UPDATE;
    }

    @Override
    public int getRelativeX() {
        return position.x;
    }

    @Override
    public int getRelativeY() {
        return position.y;
    }
    public SimpleSwordUpdate(Direction direction, Point position){
        this.direction=direction;
        this.position=position;
    }
}
