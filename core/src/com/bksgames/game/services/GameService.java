package com.bksgames.game.services;

import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.Move;
import com.bksgames.game.common.enums.PlayerColor;
import com.bksgames.game.common.Update;

import java.util.*;

public interface GameService {
    PlayerService connect(PlayerColor player);
    default Collection<Move> getLegalMoves(int x, int y, PlayerColor player){
       return getLegalMoves(new Point(x, y), player);
    }
    Collection<Move> getLegalMoves(Point position, PlayerColor player);
    boolean move(Move move, PlayerColor player);
    boolean forwardUpdate(PlayerColor color, Update update);
    boolean forwardUpdates(PlayerColor color, Collection<Update> updates);
    void startGame();
    void endTurn(PlayerColor color);
}