package com.bksgames.game.core.tiles;

import com.bksgames.game.core.utils.IsDisplayable;

/**
 * Representing single field in {@code Board}
 *
 * @author riper
 * @author jajko
 * @author typsoon
 */
public interface Tile extends IsDisplayable {
    /**
     * @return tunnel if {@code Tile} is {@code Tunnel} | otherwise {@code NULL}
     */
    default Tunnel getTunnel() {
        return null;
    }

    /**
     * @return {@code True} if hollow | otherwise {@code False}
     */
    default boolean isHollow() {
        return getTunnel() != null;
    }
}
