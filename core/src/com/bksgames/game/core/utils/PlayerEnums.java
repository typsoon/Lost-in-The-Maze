package com.bksgames.game.core.utils;

import com.bksgames.game.common.Displayable;
import com.bksgames.game.common.PlayerColor;

/**
 * Easier access to Enums associated with {@code PlayerColor}
 *
 * @author jajko
 */

public class PlayerEnums {
    /**
     * @return {@code Displayable} of {@code Nexus} owned by {@code playerColor}
     */
    public static Displayable getNexusColor(PlayerColor playerColor) {
        if (playerColor == PlayerColor.RED) return Displayable.RED_NEXUS;
        else return Displayable.BLUE_NEXUS;
    }

    /**
     * @return {@code Displayable} of {@code Minion} owned by {@code playerColor}
     */
    public static Displayable getMinionColor(PlayerColor playerColor) {
        if (playerColor == PlayerColor.RED) return Displayable.RED_MINION;
        else return Displayable.BLUE_MINION;
    }

    /**
     * @return {@code Displayable} of {@code Mirror} owned by {@code playerColor}
     */
    public static Displayable getMirrorColor(PlayerColor playerColor) {
        if (playerColor == PlayerColor.RED) return Displayable.RED_MIRROR_SLASH;
        else return Displayable.BLUE_MIRROR_SLASH;
    }

}
