package com.bksgames.game.updateData;

import com.bksgames.game.enums.Displayable;
import com.bksgames.game.enums.PlayerColor;

public interface TileUpdate extends Update {
    Displayable whatToDisplay();
    PlayerColor MinionColor();

    boolean isVisible();
}
