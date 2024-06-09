package com.bksgames.game.services;

import com.bksgames.game.common.moves.IncompleteMove;
import com.bksgames.game.core.main.Player;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.PlayerColor;
import com.bksgames.game.common.updates.Update;

import java.util.*;

public interface GameService {
    PlayerService connect(PlayerColor player);

    Collection<IncompleteMove> getLegalMoves(Point position, PlayerColor player);
    boolean acceptAction(IncompleteMove action, PlayerColor player);

    @SuppressWarnings("UnusedReturnValue")
    boolean forwardUpdate(PlayerColor color, Update update);
    void startGame();
    boolean endTurn(PlayerColor color);

    PlayerColor getWinner();
}