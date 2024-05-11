package com.bksgames.game.services;

import com.bksgames.game.core.Player;
import com.bksgames.game.enums.PlayerColor;
import com.bksgames.game.updateData.Update;

public interface PlayerService {
    PlayerColor getWatched();

    int getMaxX();
    int getMaxY();
    int getMinX();
    int getMinY();

    void pushUpdate(Update update);
    Update getUpdate();
    boolean hasUpdates();
}
