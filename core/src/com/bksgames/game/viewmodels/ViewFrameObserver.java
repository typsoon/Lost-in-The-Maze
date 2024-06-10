package com.bksgames.game.viewmodels;

import com.bksgames.game.core.utils.Point;

public interface ViewFrameObserver {

    void addLaserAtPosition(Point position);
    boolean framePassed();
}
