package com.bksgames.game.core.utils;

import com.bksgames.game.common.updates.Update;
import com.bksgames.game.core.main.updateHolders.UpdateHolder;

public interface Respawnable extends Spawnable, Vulnerable {
    /**
     * @return {@code BaseSpawnPosition} of {@code Respawnable} | {@code null} if not respawn able
     */
    Point getBaseSpawnPosition();
    default UpdateHolder<? extends Update> spawn(){
        return spawn(getBaseSpawnPosition());
    }
}
