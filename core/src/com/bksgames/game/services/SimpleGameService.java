package com.bksgames.game.services;

import com.bksgames.game.common.moves.IncompleteMove;
import com.bksgames.game.core.main.GameManager;
import com.bksgames.game.core.main.Player;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.core.moves.Action;
import com.bksgames.game.core.utils.Parameters;
import com.bksgames.game.core.main.SimpleGameManager;
import com.bksgames.game.common.PlayerColor;
import com.bksgames.game.common.updates.Update;

import java.util.*;

public  class SimpleGameService implements GameService {

    final Map<PlayerColor, PlayerService> players;
    GameManager gameManager;
    final Parameters parameters;

    Collection<Action> possibleActions;

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
    public Collection<IncompleteMove> getLegalMoves(Point position, PlayerColor player) {
        if(player!=gameManager.getCurrentPlayer()) {
            return Collections.emptyList();
        }

        possibleActions = gameManager.getLegalMoves(gameManager.getPlayers().get(player).getAbsoluteCoordinates(position));
        return possibleActions.stream().map(Action::getIncompleteMove).toList();
    }

    @Override
    public synchronized boolean acceptAction(IncompleteMove incompleteMove, PlayerColor player, Point minionPosition) {
        if(player!=gameManager.getCurrentPlayer() || gameManager.getPlayers().get(player).getAbsoluteCoordinates(minionPosition)==null) {
            return false;
        }

        Action foundAction = possibleActions.stream().filter(
                action -> action.getIncompleteMove().equals(incompleteMove)).findFirst().orElse(null);
        possibleActions.clear();
        if (foundAction != null) {
            foundAction.handle();
            return true;
        }

        return false;
    }

    @Override
    public boolean forwardUpdate(PlayerColor color, Update update) {
        players.get(color).pushUpdate(update);
        return true;
    }

    @Override
    public void startGame() {
        if(gameManager!=null && gameManager.getWinner()==null) {
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

    @Override
    public PlayerColor getWinner() {
        return gameManager.getWinner();
    }

    public SimpleGameService(Parameters parameters) {
        players = new HashMap<>();
        this.parameters = parameters;
    }
}
