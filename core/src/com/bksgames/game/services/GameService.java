package com.bksgames.game.services;

import com.bksgames.game.core.Move;
import com.bksgames.game.enums.PlayerColor;

import java.util.*;

public interface GameService {
    PlayerService connect(PlayerColor player);
    Collection<Move> getLegalMoves(int x, int y, PlayerColor player);
    Boolean move(Move move,PlayerColor player);
}