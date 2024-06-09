package com.bksgames.game.common.moves;

import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.utils.Direction;


public record Move (Point position, ActionToken type, Direction direction) {
    @Override
    public Point position() {
        return position.copy();
    }
}

