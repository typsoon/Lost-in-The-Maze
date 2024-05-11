package com.bksgames.game.core.boards;

import com.bksgames.game.core.Minion;
import com.bksgames.game.core.Nexus;
import com.bksgames.game.core.tiles.Tile;
import com.bksgames.game.enums.PlayerColor;

import java.awt.*;
import java.util.Collection;

public interface Board {

    Tile getTile(int x, int y);
    int getWidth();
    int getHeight();
    Collection<Nexus> getNexus(PlayerColor player);
    Collection<Point> getVisible(Minion minion);
}
