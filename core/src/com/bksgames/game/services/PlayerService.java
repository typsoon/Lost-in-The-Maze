package com.bksgames.game.services;

import com.bksgames.game.common.moves.IncompleteMove;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.PlayerColor;
import com.bksgames.game.common.updates.Update;

import java.util.Collection;

public interface PlayerService {
    PlayerColor getPlayerColor();

    boolean sendMove(IncompleteMove incompleteMove, Point minionPosition);

    Collection<IncompleteMove> getLegalMoves(Point position);

    void pushUpdate(Update update);
    Update getUpdate();
    boolean hasUpdates();
    void endTurn();

    PlayerColor getWinner();
}
