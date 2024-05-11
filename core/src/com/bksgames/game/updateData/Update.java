package com.bksgames.game.updateData;

import com.bksgames.game.enums.UpdateIDs;

public interface Update {
    UpdateIDs getID();

//    pass relative coordinates
    int getRelativeX();
    int getRelativeY();
}
