package com.bksgames.game.core.tiles;

import com.bksgames.game.globalClasses.enums.Displayable;

/**
 * Representing {@code Wall}
 *
 * @author typsoon
 * @author riper
 * @author jajko
 */
public class Wall implements Tile {

    //Tile
    @Override
    public Displayable getDisplayable() {
        return Displayable.WALL;
    }
}
