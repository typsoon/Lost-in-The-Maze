package com.bksgames.game.core.test.boards;

import com.bksgames.game.core.test.entities.Minion;
import com.bksgames.game.core.test.tiles.Nexus;
import com.bksgames.game.core.test.tiles.Tile;
import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.globalClasses.enums.PlayerColor;

import java.awt.*;
import java.util.List;
import java.util.Set;

public interface Board {
    static final int baseSize = 7;
    Tile getTile(int x, int y);
    int getWidth();
    int getHeight();
    List<Nexus> getNexus(PlayerColor player);
    Set<Point> getVisible(Minion minion);
    Set<Point> getNexusesVision(PlayerColor player);
    Set<Point> getLineOfSight(Point point, Direction direction);
}
