package com.bksgames.game.services;

import com.badlogic.gdx.Game;
import com.bksgames.game.core.Player;
import com.bksgames.game.enums.PlayerColor;
import com.bksgames.game.updateData.Update;

import java.util.ArrayDeque;
import java.util.Queue;

public class SimplePlayerService implements PlayerService {
    final PlayerColor watched; // color of player
    final GameService game;
    int maxX, minX, maxY, minY;

    Queue<Update> updates = new ArrayDeque<>();

    @Override
    public PlayerColor getWatched() {return watched;}

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



    public SimplePlayerService(PlayerColor watched,GameService gameService) {
        this.watched = watched;
        this.game = gameService;
    }
}
