package com.bksgames.game.core.tiles;

public interface Tile {
    // RETURNS TUNNEL IF TILE IS TUNNEL OTHERWISE NULL
    Tunnel getTunnel();
	default boolean isHollow() {
		return getTunnel()!=null;
	}
}
