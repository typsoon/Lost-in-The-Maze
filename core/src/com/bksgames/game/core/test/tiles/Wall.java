package com.bksgames.game.core.test.tiles;

import com.bksgames.game.globalClasses.enums.Displayable;

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
