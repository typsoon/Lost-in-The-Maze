package com.bksgames.game.core;

import com.bksgames.game.actionsHandlers.ActionHandler;
import com.bksgames.game.actionsHandlers.ActionHandlerFactory;
import com.bksgames.game.core.boards.Board;
import com.bksgames.game.core.boards.SquareBoardFactory;
import com.bksgames.game.enums.MoveTypes;
import com.bksgames.game.enums.PlayerColor;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SimpleGameManager implements GameManager {
    private final Board board;
    private final Map<PlayerColor, Player> players;
    private final Map<MoveTypes, ActionHandler> moveHandlers;
    PlayerColor activePlayer;
    @Override
    public Boolean makeMove(Move move) {
        if(players.get(activePlayer).hasMinion(move.x(),move.y())
        ||
        !getLegalMoves(move.x(), move.y(), activePlayer).contains(move)) {
            return false;
        }
        moveHandlers.get(move.type()).handle(move);
        return true;
    }
    @Override
    public Collection<Move> getLegalMoves(int x, int y, PlayerColor color) {
        return List.of();
    }
    public SimpleGameManager(Parameters parameters) {
        this.board = SquareBoardFactory.CreateSBFor2Players(parameters);
        moveHandlers = new HashMap<>();
        for(MoveTypes moveType : MoveTypes.values()) {
            moveHandlers.put(moveType,ActionHandlerFactory.CreateActionHandler(moveType,board));
        }
        players = new HashMap<>();
        activePlayer = PlayerColor.BLUE;
     //   players.put();
     //   players.put();
    }
}
