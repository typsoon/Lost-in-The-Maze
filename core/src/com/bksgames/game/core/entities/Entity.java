package com.bksgames.game.core.entities;

import com.bksgames.game.core.utils.KnownPosition;
import com.bksgames.game.core.utils.Vulnerable;
import com.bksgames.game.enums.Displayable;

public interface Entity extends Vulnerable, KnownPosition {
    void spawn(int x, int y, int hitPoints);
    Displayable getDiplayable();
}
