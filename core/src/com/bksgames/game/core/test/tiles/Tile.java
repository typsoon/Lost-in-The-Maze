package com.bksgames.game.core.test.tiles;

import com.bksgames.game.globalClasses.enums.Displayable;

public interface Tile {
    // RETURNS TUNNEL IF TILE IS TUNNEL OTHERWISE NULL
    Displayable getDisplayable();
    Tunnel getTunnel();
	default boolean isHollow() {
		return getTunnel()!=null;
	}
}
