package com.bksgames.game.core.utils;

import com.bksgames.game.common.updates.Update;
import com.bksgames.game.core.main.updateHolders.UpdateHolder;

/** Objects can be damaged and killed
 * @author riper
 * @author typsoon
 */
public interface Vulnerable extends KnownPosition{
    /**
     * @return amount of remaining hitpoints
     */
    @SuppressWarnings("unused")
    int getHitPoints();
    /**
     * Deal damage
     * @param sourceOfDamage source of received damage
     * @return {@code UpdateHolder} with death update if killed | {@code false} otherwise
     */
    UpdateHolder<? extends Update> damage(SourceOfDamage sourceOfDamage);
}
