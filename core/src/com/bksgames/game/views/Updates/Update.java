package com.bksgames.game.views.Updates;

import com.bksgames.game.core.Player;

public interface Update {
    String getKey();

//    pass relative coordinates
    int getRelativeX();
    int getRelativeY();
}
