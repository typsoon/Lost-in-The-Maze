package com.bksgames.game.core.boards;

import com.bksgames.game.core.Nexus;
import com.bksgames.game.core.Parameters;
import com.bksgames.game.core.Player;
import com.bksgames.game.core.tiles.Tile;
import com.bksgames.game.core.tiles.Tunnel;
import com.bksgames.game.core.tiles.Wall;
import com.bksgames.game.enums.PlayerColor;

import java.awt.*;
import java.util.*;
import java.util.List;

public class SquareBoard implements Board {

    final Tile[][] grid;
    final int size;
    final Map<PlayerColor, Collection<Nexus>> playerNexuses;

    @Override
    public Tile getTile(int x, int y) {
        if(x>=size || y>=size || x<0 || y<0)      //exception do dodania
            return null;
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
    public Collection<Nexus> getNexus(PlayerColor player) {
        return playerNexuses.get(player);
    }

    SquareBoard(int size){
        this.size = size;
        playerNexuses = new HashMap<>();
        grid = new Tile[size][size];
    }
}
