package com.bksgames.game.service;

import com.bksgames.game.core.Player;
import com.bksgames.game.views.Updates.Update;

//Should we rename this to PlayerService?
public interface PlayerService {
    Player.PlayerColor getWatched();

    int getMaxX();
    int getMaxY();
    int getMinX();
    int getMinY();

    void pushUpdate(Update update);
    Update getUpdate();
    boolean hasUpdates();
}