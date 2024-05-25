package com.bksgames.game.core.utils;

import com.bksgames.game.common.enums.ActionToken;

/**
 *
 *
 */
public interface Interactive {
    int getActionPoints();
    boolean makeAction(ActionToken actionToken);
}
