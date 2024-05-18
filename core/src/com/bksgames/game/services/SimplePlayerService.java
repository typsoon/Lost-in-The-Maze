package com.bksgames.game.services;

import com.bksgames.game.core.utils.Point;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.globalClasses.enums.PlayerColor;
import com.bksgames.game.globalClasses.Update;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

public class SimplePlayerService implements PlayerService {
    final PlayerColor playerColor; // color of player
    final GameService gameService;
    int maxX, minX, maxY, minY;

//    TODO: BIG TODO, CHANGE THIS TO STREAM
//    TODO: TUDUDU TUDUDU
    Queue<Update> updates = new ArrayDeque<>();

    @Override
    public PlayerColor getPlayerColor() {return playerColor;}

    @Override
    public boolean sendMove(Move move) {
        return gameService.move(move, getPlayerColor());
    }

    @Override
    public Collection<Move> getLegalMoves(Point position) {
        return gameService.getLegalMoves(position, getPlayerColor());
    }

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

    public SimplePlayerService(PlayerColor playerColor, GameService gameService) {
        this.playerColor = playerColor;
        this.gameService = gameService;
    }
}
