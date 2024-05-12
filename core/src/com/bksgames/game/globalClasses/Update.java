package com.bksgames.game.globalClasses;

import com.bksgames.game.globalClasses.enums.UpdateIDs;

public interface Update {
    UpdateIDs getID();

//    pass relative coordinates
    int getRelativeX();
    int getRelativeY();
}
