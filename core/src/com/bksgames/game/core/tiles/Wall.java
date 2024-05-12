package com.bksgames.game.core.tiles;

import com.bksgames.game.enums.Displayable;

public class Wall implements Tile{

    @Override
    public Displayable getDisplayable() {
        return Displayable.WALL;
    }

    @Override
    public Tunnel getTunnel() {
        return null;
    }
}
