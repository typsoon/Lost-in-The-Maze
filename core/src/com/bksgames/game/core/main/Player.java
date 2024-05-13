package com.bksgames.game.core.main;

import com.bksgames.game.core.entities.Minion;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Player {
    Point mainNexus;
    Collection<Minion> minions = new ArrayList<>();
    Set<Point> visibleTiles = new HashSet<>();

    Point getRelativeCoordinates(Point coordinates)
    {
        return new Point(coordinates.x-mainNexus.x, coordinates.y-mainNexus.y);
    }
    Point getAbsoluteCoordinates(Point coordinates)
    {
        return new Point(coordinates.x+mainNexus.x, coordinates.x+mainNexus.y);
    }
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
    public Minion getMinion(int x,int y){
        for(Minion minion : minions){
            if(minion.getX() == x && minion.getY() == y){return minion;}
        }
        return null;
    }
}
