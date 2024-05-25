package com.bksgames.game.common;

import com.bksgames.game.common.enums.Displayable;

public interface TileUpdate extends Update {
    Displayable whatToDisplay();
    boolean isVisible();
}
