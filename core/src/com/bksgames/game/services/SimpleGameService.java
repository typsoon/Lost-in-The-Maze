package com.bksgames.game.services;

import com.bksgames.game.core.main.GameManager;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.moves.Move;
import com.bksgames.game.core.utils.Parameters;
import com.bksgames.game.core.main.SimpleGameManager;
import com.bksgames.game.common.PlayerColor;
import com.bksgames.game.common.updates.Update;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SimpleGameService implements GameService {

    final Map<PlayerColor, PlayerService> players;
    GameManager gameManager;
    final Parameters parameters;
    @Override
    public PlayerService connect(PlayerColor player) {
        if(players.containsKey(player)) {
            throw new IllegalArgumentException("Player already connected");
        }
        PlayerService service = new SimplePlayerService(player,this);
        players.put(player, service);
        return service;
    }

    @Override
    public Collection<Move> getLegalMoves(Point position, PlayerColor player) {
        Collection<Move> relativeLegalMoves = new ArrayList<>();
        if(player!=gameManager.getCurrentPlayer()) {
            return relativeLegalMoves;
        }
        for(Move move : gameManager.getLegalMoves(gameManager.getPlayers().get(gameManager.getCurrentPlayer()).getAbsoluteCoordinates(position))) {
            relativeLegalMoves.add(new Move(
                    gameManager.getPlayers().get(gameManager.getCurrentPlayer()).getRelativeCoordinates(move.position()),
                    move.type(),move.direction()));
        }
        return relativeLegalMoves;
    }

    @Override
    public boolean move(Move move, PlayerColor player) {
        if(player!=gameManager.getCurrentPlayer()) {
            return false;
        }
        return gameManager.makeMove(new Move(
                gameManager.getPlayers().get(gameManager.getCurrentPlayer()).getAbsoluteCoordinates(move.position()),
                move.type(),move.direction()));
    }

    @Override
    public boolean forwardUpdate(PlayerColor color, Update update) {
        players.get(color).pushUpdate(update);
        return true;
    }

    @Override
    public void startGame() {
        if(gameManager!=null) {
            throw new IllegalStateException("Game already started");
        }
        gameManager = new SimpleGameManager(this,parameters);
    }

    @Override
    public boolean endTurn(PlayerColor player) {
        if(gameManager.getCurrentPlayer()!=player) {
            return false;
        }
        gameManager.endTurn();
        return true;
    }

    public SimpleGameService(Parameters parameters) {
        players = new HashMap<>();
        this.parameters = parameters;
    }
}
