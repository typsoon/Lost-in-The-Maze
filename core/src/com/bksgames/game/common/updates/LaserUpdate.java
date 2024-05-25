package com.bksgames.game.common.updates;

import com.bksgames.game.common.utils.Direction;

public interface LaserUpdate extends Update {
    Direction getDirection();
    Direction getDeflectedDirection();
}
