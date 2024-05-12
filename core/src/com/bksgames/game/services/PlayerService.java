package com.bksgames.game.services;

import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.globalClasses.enums.PlayerColor;
import com.bksgames.game.globalClasses.Update;

import java.util.Collection;

public interface PlayerService {
    PlayerColor getPlayerColor();

    boolean sendMove(Move move);
    Collection<Move> getLegalMoves(int x, int y);

    void pushUpdate(Update update);
    Update getUpdate();
    boolean hasUpdates();
}
