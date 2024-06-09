package com.bksgames.game.viewmodels.laserHandling;

import com.bksgames.game.core.utils.Point;

public interface ViewLaserHandler {

    void addLaserAtPosition(Point position);
    boolean framePassed();
}
