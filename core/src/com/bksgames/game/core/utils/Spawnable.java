package com.bksgames.game.core.utils;

public interface Spawnable {
    void spawn(Point position);
    /**
     * @return {@code BaseSpawnPositio} of {@code Spawnable} | {@code null} if not respawn able
     */
    Point getBaseSpawnPosition();
}
