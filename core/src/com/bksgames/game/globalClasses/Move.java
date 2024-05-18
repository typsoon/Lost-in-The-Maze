package com.bksgames.game.globalClasses;

import com.bksgames.game.core.utils.Point;
import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.globalClasses.enums.MoveTypes;


public record Move (Point position, MoveTypes type, Direction direction) {
    @Override
    public Point position() {
        return position.getPosition();
    }
}

