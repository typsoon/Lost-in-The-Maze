package com.bksgames.game.core.utils;

import com.bksgames.game.common.Displayable;
import com.bksgames.game.common.PlayerColor;
import com.bksgames.game.core.tiles.Mirror;

/**
 * Easier access to Enums associated with {@code PlayerColor}
 *
 * @author jajko
 * @author riper
 */

public class PlayerEnums {
    /**
     * @return {@code Displayable} of {@code Nexus} owned by {@code playerColor}
     */
    public static Displayable getNexusDisplayable(PlayerColor playerColor) {
        if (playerColor == PlayerColor.RED) return Displayable.RED_NEXUS;
        else return Displayable.BLUE_NEXUS;
    }

    /**
     * @return {@code Displayable} of {@code Minion} owned by {@code playerColor}
     */
    public static Displayable getMinionDisplayable(PlayerColor playerColor) {
        if (playerColor == PlayerColor.RED) return Displayable.RED_MINION;
        else return Displayable.BLUE_MINION;
    }

    /**
     * @return {@code Displayable} of {@code Mirror} owned by {@code playerColor}
     */
    public static Displayable getMirrorDisplayable(PlayerColor playerColor, Mirror.Orientation orientation) {
        if(orientation== Mirror.Orientation.SLASH)
        {
            if (playerColor == PlayerColor.RED) return Displayable.RED_MIRROR_SLASH;
            else return Displayable.BLUE_MIRROR_SLASH;
        }
        else
        {
            if (playerColor == PlayerColor.RED) return Displayable.RED_MIRROR_BACKSLASH;
            else return Displayable.BLUE_MIRROR_BACKSLASH;
        }
    }

}
