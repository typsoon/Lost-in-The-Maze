package com.bksgames.game.globalClasses;

import com.bksgames.game.core.utils.Point;
import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.globalClasses.enums.ActionToken;


public record Move (Point position, ActionToken type, Direction direction) {
    @Override
    public Point position() {
        return position.getPosition();
    }
}

