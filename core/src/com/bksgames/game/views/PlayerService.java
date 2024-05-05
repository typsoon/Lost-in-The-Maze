package com.bksgames.game.views;

import com.bksgames.game.core.Player;

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