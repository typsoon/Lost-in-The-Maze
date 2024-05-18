package com.bksgames.game.services;

import com.bksgames.game.core.main.GameManager;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.core.utils.Parameters;
import com.bksgames.game.core.main.SimpleGameManager;
import com.bksgames.game.globalClasses.enums.PlayerColor;
import com.bksgames.game.globalClasses.Update;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SimpleGameService implements GameService {

    Map<PlayerColor, PlayerService> players;
    GameManager gameManager;
    Parameters parameters;
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
    public Collection<Move> getLegalMoves(Point position, PlayerColor player) {
        if(player!=gameManager.getCurrentPlayer()) {
            return null;
        }
        Collection<Move> relativeLegalMoves = new ArrayList<>();
        for(Move move : gameManager.getLegalMoves(gameManager.getPlayers().get(gameManager.getCurrentPlayer()).getAbsoluteCoordinates(position), player)) {
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
    public boolean ForwardUpdate(PlayerColor color, Update update) {
        //System.out.println(color + " " + update);
        players.get(color).pushUpdate(update);
        return true;
    }
    @Override
    public boolean ForwardUpdates(PlayerColor color, Collection<Update> updates) {
       for(Update u : updates) {
           players.get(color).pushUpdate(u);
       }
       return true;
    }

    @Override
    public void StartGame() {
        gameManager = new SimpleGameManager(this,parameters);
    }

    public SimpleGameService(Parameters parameters) {
        players = new HashMap<>();
        this.parameters = parameters;
    }
}
