package com.bksgames.game.common.updates;

public interface Update {
    UpdateIDs getID();

//    pass relative coordinates
    int getRelativeX();
    int getRelativeY();
}
