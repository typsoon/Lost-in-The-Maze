package com.bksgames.game.services;

import com.bksgames.game.core.utils.Point;
import com.bksgames.game.core.actions.Action;
import com.bksgames.game.common.PlayerColor;
import com.bksgames.game.common.updates.Update;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

public class SimplePlayerService implements PlayerService {
    final PlayerColor playerColor; // color of player
    final GameService gameService;

//    TODO: BIG TODO, CHANGE THIS TO STREAM
//    TODO: TUDUDU TUDUDU
    final Queue<Update> updates = new ArrayDeque<>();

    @Override
    public PlayerColor getPlayerColor() {return playerColor;}

    @Override
    public boolean sendMove(Action move) {
        return gameService.move(move, getPlayerColor());
    }

    @Override
    public Collection<Action> getLegalMoves(Point position) {
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

    @Override
    public void endTurn() {
        if (!gameService.endTurn(playerColor)) {
            throw new IllegalStateException("Player ending turn is not the active player");
        }
    }

    public SimplePlayerService(PlayerColor playerColor, GameService gameService) {
        this.playerColor = playerColor;
        this.gameService = gameService;
    }
}
