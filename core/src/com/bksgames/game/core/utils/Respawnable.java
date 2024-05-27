package com.bksgames.game.core.utils;

public interface Respawnable extends Spawnable, Vulnerable {
    /**
     * @return {@code BaseSpawnPosition} of {@code Respawnable} | {@code null} if not respawn able
     */
    Point getBaseSpawnPosition();
    default void spawn(){
        spawn(getBaseSpawnPosition());
    }
}
