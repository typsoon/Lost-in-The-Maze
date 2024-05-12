package com.bksgames.game.core;

import com.bksgames.game.enums.Displayable;

public interface Entity extends Vulnerable, KnownPosition{
    void spawn(int x, int y, int hitPoints);
    Displayable getDiplayable();
}
