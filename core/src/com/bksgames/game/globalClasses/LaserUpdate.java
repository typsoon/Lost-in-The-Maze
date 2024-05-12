package com.bksgames.game.globalClasses;

import com.bksgames.game.globalClasses.enums.Direction;

public interface LaserUpdate extends Update {
    Direction getDirection();
    Direction getDeflectedDirection();
}
