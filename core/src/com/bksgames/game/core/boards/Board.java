package com.bksgames.game.core.boards;

import com.bksgames.game.core.entities.Minion;
import com.bksgames.game.core.tiles.Nexus;
import com.bksgames.game.core.tiles.Tile;
import com.bksgames.game.enums.Direction;
import com.bksgames.game.enums.PlayerColor;

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
