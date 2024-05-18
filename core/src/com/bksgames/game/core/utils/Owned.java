package com.bksgames.game.core.utils;

import com.bksgames.game.globalClasses.enums.PlayerColor;

/**
 * Objects are owned by {@code Player} with {@code PlayerColor}
 * @author riper
 */
public interface Owned {
    /**
     * @return {@code PlayerColor} of owner
     */
    PlayerColor getOwner();
}
