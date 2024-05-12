package com.bksgames.game.services;

import com.bksgames.game.core.GameManager;
import com.bksgames.game.core.Move;
import com.bksgames.game.core.Parameters;
import com.bksgames.game.core.SimpleGameManager;
import com.bksgames.game.enums.PlayerColor;
import com.bksgames.game.updateData.Update;

import java.io.BufferedOutputStream;
import java.util.Collection;
import java.util.Map;

public class SimpleGameService implements GameService {

    Map<PlayerColor, PlayerService> players;
    GameManager gameManager;
    @Override
    public PlayerService connect(PlayerColor player) {
        PlayerService service = new SimplePlayerService(player,this);
        if(players.containsKey(player)) {
            throw new IllegalArgumentException("Player already connected");
        }
        players.put(player, service);
        return service;
    }

    @Override
    public Collection<Move> getLegalMoves(int x, int y, PlayerColor player) {

        return gameManager.getLegalMoves(x,y,player);
    }

    @Override
    public Boolean move(Move move, PlayerColor player) {
        return gameManager.makeMove(move);
    }

    @Override
    public Boolean SendUpdate(PlayerColor color, Update update) {
        players.get(color).pushUpdate(update);
        return true;
    }

    public SimpleGameService(Parameters parameters) {
        gameManager = new SimpleGameManager(parameters);
    }
}
