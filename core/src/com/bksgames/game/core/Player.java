package com.bksgames.game.core;

import com.bksgames.game.enums.PlayerColor;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class Player {
    Point mainNexus;
    Collection<Minion> minions = new ArrayList<>();
    Collection<Point> visibleTiles = new ArrayList<>();

    void addMinion(Minion minion) {
        minions.add(minion);
    }
    boolean isVisible(Point point) {
        return visibleTiles.contains(point);
    }
    void removeVisibleTiles(Collection<Point> visibleTiles) {
        this.visibleTiles.removeAll(visibleTiles);
    }
    void addVisibleTiles(Collection<Point> visibleTiles) {
        this.visibleTiles.addAll(visibleTiles);
    }
    Player(Point mainNexus) {
        this.mainNexus =new Point(mainNexus);
    }
}
