package com.bksgames.game.core;

import com.bksgames.game.enums.PlayerColor;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Player {
    Point mainNexus;
    Collection<Minion> minions = new ArrayList<>();
    Set<Point> visibleTiles = new HashSet<>();

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
    boolean hasMinion(int x,int y){
        for(Minion minion : minions){
            if(minion.x == x && minion.y == y){return true;}
        }
        return false;
    }
}
