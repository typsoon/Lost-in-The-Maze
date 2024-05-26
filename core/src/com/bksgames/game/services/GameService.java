package com.bksgames.game.services;

import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.moves.Move;
import com.bksgames.game.common.PlayerColor;
import com.bksgames.game.common.updates.Update;

import java.util.*;

public interface GameService {
    PlayerService connect(PlayerColor player);
    default Collection<Move> getLegalMoves(int x, int y, PlayerColor player){
       return getLegalMoves(new Point(x, y), player);
    }
    Collection<Move> getLegalMoves(Point position, PlayerColor player);
    boolean move(Move move, PlayerColor player);
    boolean forwardUpdate(PlayerColor color, Update update);
    void startGame();
    void endTurn(PlayerColor color);
}