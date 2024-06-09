package com.bksgames.game.services;

import com.bksgames.game.core.utils.Point;
import com.bksgames.game.core.actions.Action;
import com.bksgames.game.common.PlayerColor;
import com.bksgames.game.common.updates.Update;

import java.util.Collection;

public interface PlayerService {
    PlayerColor getPlayerColor();

    boolean sendMove(Action move);
    default Collection<Action> getLegalMoves(int x, int y){
        return getLegalMoves(new Point(x, y));
    }
    Collection<Action> getLegalMoves(Point position);

    void pushUpdate(Update update);
    Update getUpdate();
    boolean hasUpdates();
    void endTurn();
}
