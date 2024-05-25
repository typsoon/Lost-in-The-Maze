package com.bksgames.game.core.utils;

import com.bksgames.game.common.moves.ActionToken;

/**
 *
 *
 */
public interface Interactive {
    int getActionPoints();
    boolean makeAction(ActionToken actionToken);
}
