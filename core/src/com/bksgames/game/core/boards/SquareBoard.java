package com.bksgames.game.core.boards;

import com.bksgames.game.core.entities.Entity;
import com.bksgames.game.core.entities.Minion;
import com.bksgames.game.core.tiles.*;
import com.bksgames.game.core.utils.SourceOfDamage;
import com.bksgames.game.enums.Direction;
import com.bksgames.game.enums.PlayerColor;
import com.bksgames.game.core.tiles.Nexus;
import com.bksgames.game.core.tiles.Tile;
import com.bksgames.game.core.tiles.Wall;
import com.bksgames.game.globalClasses.enums.PlayerColor;

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
        visible.add(new Point(minion.getX(), minion.getY()));
        for(Direction d : Direction.values()){
            Point point = new Point(minion.getX(), minion.getY());
            visible.addAll(getLineOfSight(point, d));
        }
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

    @Override
    public Set<Point> getLineOfSight(Point point, Direction direction) {
        Map<Mirror, Set<Direction>> mirrorMap = new HashMap<>();
        Set<Point> lineofsight = new HashSet<>();

        Tile currentTile = getTile(point.x, point.y);
        while(currentTile.isHollow()){
            Direction.next(point, direction);
            lineofsight.add(point);
            Tunnel currentTunnel = currentTile.getTunnel();
            if(currentTunnel.getMirror()!=null){
                if(!mirrorMap.containsKey(currentTunnel.getMirror())){
                    mirrorMap.put(currentTunnel.getMirror(), Set.of(direction));
                }
                else{
                    if(!mirrorMap.get(currentTunnel.getMirror()).contains(direction)){
                        mirrorMap.get(currentTunnel.getMirror()).add(direction);
                    }
                    else break;
                }
                direction = currentTunnel.getMirror().deflect(direction);
            }
        }
        return lineofsight;
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
