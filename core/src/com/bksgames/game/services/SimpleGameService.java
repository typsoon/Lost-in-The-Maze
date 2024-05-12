package com.bksgames.game.services;

import com.bksgames.game.core.main.GameManager;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.core.utils.Parameters;
import com.bksgames.game.core.main.SimpleGameManager;
import com.bksgames.game.globalClasses.enums.PlayerColor;
import com.bksgames.game.globalClasses.Update;

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
    public Collection<Move> getLegalMoves(int x, int y, PlayerColor player) {

        return gameManager.getLegalMoves(x,y,player);
    }

    @Override
    public Boolean move(Move move, PlayerColor player) {
        return gameManager.makeMove(move);
    }

    @Override
    public Boolean ForwardUpdate(PlayerColor color, Update update) {
        //System.out.println(color + " " + update);
        players.get(color).pushUpdate(update);
        return true;
    }
    @Override
    public Boolean ForwardUpdates(PlayerColor color, Collection<Update> updates) {
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
//TEST
    public static void main(String[] args) {
        SimpleGameService gameService = new SimpleGameService(new Parameters());
        PlayerService blue = gameService.connect(PlayerColor.BLUE);
        PlayerService red = gameService.connect(PlayerColor.RED);
        gameService.StartGame();
    }
}
