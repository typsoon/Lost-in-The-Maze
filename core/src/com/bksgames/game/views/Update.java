package com.bksgames.game.views;

import com.bksgames.game.core.Player;

public interface Update {
    String whatToDisplay();

    int getRelativeX(Player.PlayerColor watched);
    int getRelativeY(Player.PlayerColor watched);
}
