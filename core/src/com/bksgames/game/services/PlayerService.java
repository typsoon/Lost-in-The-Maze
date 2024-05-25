package com.bksgames.game.services;

import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.moves.Move;
import com.bksgames.game.common.PlayerColor;
import com.bksgames.game.common.updates.Update;

import java.util.Collection;

public interface PlayerService {
    PlayerColor getPlayerColor();

    boolean sendMove(Move move);
    default Collection<Move> getLegalMoves(int x, int y){
        return getLegalMoves(new Point(x, y));
    }
    Collection<Move> getLegalMoves(Point position);

    void pushUpdate(Update update);
    Update getUpdate();
    boolean hasUpdates();
}
