package com.bksgames.game.core;

import com.bksgames.game.enums.PlayerColor;

import java.util.Collection;

public interface GameManager  {
    Boolean makeMove(Move move);
    Collection<Move> getLegalMoves(int x, int y, PlayerColor color);

}
