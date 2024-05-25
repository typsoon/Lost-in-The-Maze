package com.bksgames.game.common.updates;

import com.bksgames.game.common.Displayable;

public interface TileUpdate extends Update {
    Displayable whatToDisplay();
    boolean isVisible();
}
