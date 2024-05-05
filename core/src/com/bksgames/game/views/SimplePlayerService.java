package com.bksgames.game.views;

import com.bksgames.game.core.Player;

import java.util.ArrayDeque;
import java.util.Queue;

public class SimplePlayerService implements PlayerService {
    final Player.PlayerColor watched;

    int maxX, minX, maxY, minY;

    Queue<Update> updates = new ArrayDeque<>();

    @Override
    public Player.PlayerColor getWatched() {return watched;}

    @Override
    public int getMaxX() {return maxX;}

    @Override
    public int getMaxY() {return maxY;}

    @Override
    public int getMinX() {return minX;}

    @Override
    public int getMinY() {return minY;}

    @Override
    public void pushUpdate(Update update) {
        updates.add(update);
    }

    @Override
    public Update getUpdate() {
        return updates.poll();
    }

    @Override
    public boolean hasUpdates() {
        return !updates.isEmpty();
    }


    public SimplePlayerService(Player.PlayerColor watched) {this.watched = watched;}
}
