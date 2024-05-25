package com.bksgames.game.common;

import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.enums.Direction;
import com.bksgames.game.common.enums.ActionToken;


public record Move (Point position, ActionToken type, Direction direction) {
    @Override
    public Point position() {
        return position.getPosition();
    }
}

