package com.bksgames.game.core;

import com.bksgames.game.core.boards.Board;
import com.bksgames.game.core.boards.SquareBoardFactory;
import com.bksgames.game.enums.PlayerColor;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleGameManager implements GameManager {
    private final Board board;
    private final Map<PlayerColor, Player> players;
    @Override
    public Boolean makeMove(Move move) {
        return null;
    }

    @Override
    public Collection<Move> getLegalMoves(int x, int y, PlayerColor color) {
        return List.of();
    }
    public SimpleGameManager(Parameters parameters) {
        this.board = SquareBoardFactory.CreateSBFor2Players(parameters);
        players = new HashMap<>();
     //   players.put();
     //   players.put();
    }
}
