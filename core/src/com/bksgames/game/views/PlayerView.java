package com.bksgames.game.views;

import com.bksgames.game.core.Player;

public interface PlayerView {
    Player.PlayerColor getWatched();

    int getMaxX();
    int getMaxY();
    int getMinX();
    int getMinY();

    void PushUpdate(Update update);
    Update getUpdate();
    boolean hasUpdates();
}