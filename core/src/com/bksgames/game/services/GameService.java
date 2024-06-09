package com.bksgames.game.services;

import com.bksgames.game.core.utils.Point;
import com.bksgames.game.core.actions.Action;
import com.bksgames.game.common.PlayerColor;
import com.bksgames.game.common.updates.Update;

import java.util.*;

public interface GameService {
    PlayerService connect(PlayerColor player);
    default Collection<Action> getLegalMoves(int x, int y, PlayerColor player){
       return getLegalMoves(new Point(x, y), player);
    }
    Collection<Action> getLegalMoves(Point position, PlayerColor player);
    boolean move(Action move, PlayerColor player);

    @SuppressWarnings("UnusedReturnValue")
    boolean forwardUpdate(PlayerColor color, Update update);
    void startGame();
    boolean endTurn(PlayerColor color);
}