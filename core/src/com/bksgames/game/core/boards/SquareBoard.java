package com.bksgames.game.core.boards;

import com.bksgames.game.core.entities.Minion;
import com.bksgames.game.core.tiles.Nexus;
import com.bksgames.game.core.tiles.Tile;
import com.bksgames.game.core.tiles.Wall;
import com.bksgames.game.enums.PlayerColor;

import java.awt.*;
import java.util.*;
import java.util.List;

public class SquareBoard implements Board {

    final Tile[][] grid;
    final int size;
    final Map<PlayerColor, List<Nexus>> playerNexuses;

    @Override
    public Tile getTile(int x, int y) {
        if(x>=size || y>=size || x<0 || y<0)      //exception do dodania
            return new Wall();
        return grid[x][y];
    }

    @Override
    public int getWidth() {
        return size;
    }

    @Override
    public int getHeight() {
        return size;
    }

    @Override
    public List<Nexus> getNexus(PlayerColor player) {
        return playerNexuses.get(player);
    }

    @Override
    public Set<Point> getVisible(Minion minion) {
        if(!getTile(minion.getX(),minion.getY()).isHollow())
            throw new IllegalArgumentException("Minion is in wall!");
        Set<Point> visible = new HashSet<>();
        visible.add(new Point(minion.getX(),minion.getY()));

        Point act = new Point(minion.getX(),minion.getY());

        act.x++;
        while (getTile(act.x,act.y).isHollow()) {
            visible.add(new Point(act.x,act.y));
            act.x++;
        }
        visible.add(new Point(act.x,act.y));
        act.x = minion.getX();

        act.x--;
        while (getTile(act.x,act.y).isHollow()) {
            visible.add(new Point(act.x,act.y));
            act.x--;
        }
        visible.add(new Point(act.x,act.y));
        act.x = minion.getX();

        act.y++;
        while (getTile(act.x,act.y).isHollow()) {
            visible.add(new Point(act.x,act.y));
            act.y--;
        }
        visible.add(new Point(act.x,act.y));
        act.y=minion.getY();

        act.y--;
        while (getTile(act.x,act.y).isHollow()) {
            visible.add(new Point(act.x,act.y));
            act.y--;
        }
        visible.add(new Point(act.x,act.y));

        return visible;
    }

    @Override
    public Set<Point> getNexusesVision(PlayerColor player) {
        Set<Point> vision = new HashSet<>();
        for(Nexus nexus: playerNexuses.get(player))
        {
            vision.addAll(getNexusBase(nexus));
        }
        return vision;
    }

    SquareBoard(int size){
        this.size = size;
        playerNexuses = new HashMap<>();
        grid = new Tile[size][size];
    }

    private Set<Point> getNexusBase(Nexus nexus){
        Set<Point> base = new HashSet<>();
        Point actFP = new Point(nexus.getX()-baseSize/2,nexus.getY()-baseSize/2);
        for(int x=0;x<baseSize;x++)
        {

            actFP.y = nexus.getY()-baseSize/2;
            for(int y=0;y<baseSize;y++)
            {
                base.add(new Point(actFP));
                actFP.y++;
            }
            actFP.x++;
        }
        return base;
    }
}
