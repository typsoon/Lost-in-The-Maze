package com.bksgames.game.services;

import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.globalClasses.enums.PlayerColor;
import com.bksgames.game.globalClasses.Update;

import java.util.*;

public interface GameService {
    PlayerService connect(PlayerColor player);
    Collection<Move> getLegalMoves(int x, int y, PlayerColor player);
    Boolean move(Move move,PlayerColor player);
    Boolean ForwardUpdate(PlayerColor color, Update update);
    Boolean ForwardUpdates(PlayerColor color, Collection<Update> updates);
    void StartGame();
}