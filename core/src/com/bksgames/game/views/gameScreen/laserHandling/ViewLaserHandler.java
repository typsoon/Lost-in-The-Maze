package com.bksgames.game.views.gameScreen.laserHandling;

import com.bksgames.game.core.utils.Point;

public interface ViewLaserHandler {

    void addLaserAtPosition(Point position);
    void framePassed();
}
