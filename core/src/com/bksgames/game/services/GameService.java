package com.bksgames.game.services;

import com.bksgames.game.core.utils.Point;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.globalClasses.enums.PlayerColor;
import com.bksgames.game.globalClasses.Update;

import java.util.*;

public interface GameService {
    PlayerService connect(PlayerColor player);
    default Collection<Move> getLegalMoves(int x, int y, PlayerColor player){
       return getLegalMoves(new Point(x, y), player);
    }
    Collection<Move> getLegalMoves(Point position, PlayerColor player);
    boolean move(Move move, PlayerColor player);
    boolean ForwardUpdate(PlayerColor color, Update update);
    boolean ForwardUpdates(PlayerColor color, Collection<Update> updates);
    void StartGame();
}