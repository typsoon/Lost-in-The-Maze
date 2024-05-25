package com.bksgames.game.common;

import com.bksgames.game.common.enums.Direction;

public interface LaserUpdate extends Update {
    Direction getDirection();
    Direction getDeflectedDirection();
}
