package com.bksgames.game.core;

import java.util.Collection;

public interface BoardInfo {
    Player getPlayer();
    Player setPlayer(Player player);

    boolean hasWon(Player player);

    Nexus getNexus(Player player);

    Collection<Minion> getMinions(Player player);
}
