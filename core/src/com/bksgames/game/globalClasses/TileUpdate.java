package com.bksgames.game.globalClasses;

import com.bksgames.game.globalClasses.enums.Displayable;

public interface TileUpdate extends Update {
    Displayable whatToDisplay();
    boolean isVisible();
}
