package com.bksgames.game.core.utils;

import com.bksgames.game.globalClasses.enums.ActionToken;

/**
 *
 *
 */
public interface Interactive {
    int getActionPoints();
    boolean makeAction(ActionToken actionToken);
}
