package com.bksgames.game.core.test.entities;

import com.bksgames.game.core.test.utils.KnownPosition;
import com.bksgames.game.core.test.utils.Vulnerable;
import com.bksgames.game.globalClasses.enums.Displayable;

import java.awt.*;

public interface Entity extends Vulnerable, KnownPosition {
    void spawn(Point position, int hitPoints);
    Displayable getDiplayable();
}
