package com.bksgames.game.core.entities;

import com.bksgames.game.core.utils.KnownPosition;
import com.bksgames.game.core.utils.Vulnerable;
import com.bksgames.game.globalClasses.enums.Displayable;

import java.awt.*;

public interface Entity extends Vulnerable, KnownPosition {
    void spawn(Point position, int hitPoints);
    Displayable getDiplayable();
}
