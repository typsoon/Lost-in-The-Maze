package com.bksgames.game.core;

import com.bksgames.game.enums.PlayerColor;
import com.bksgames.game.updateData.Update;

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
    Set<Point> updateVisibleTiles(Set<Point> updates){
        Set<Point> diff = new HashSet<>();
        for(Point point : updates){
            if(!visibleTiles.contains(point)){
                diff.add(point);
            }
        }
        for(Point point : visibleTiles){
            if(!updates.contains(point)){
                diff.add(point);
            }
        }
        visibleTiles = updates;
        return diff;
    }
    Player(Point mainNexus) {
        this.mainNexus =new Point(mainNexus);
    }
    Minion getMinion(int x,int y){
        for(Minion minion : minions){
            if(minion.x == x && minion.y == y){return minion;}
        }
        return null;
    }
}
