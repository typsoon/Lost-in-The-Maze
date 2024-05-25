package com.bksgames.game.common;

import com.bksgames.game.common.enums.UpdateIDs;

public interface Update {
    UpdateIDs getID();

//    pass relative coordinates
    int getRelativeX();
    int getRelativeY();
}
