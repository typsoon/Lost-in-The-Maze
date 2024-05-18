package com.bksgames.game.core.utils;

/** Objects can be damaged and killed
 * @author riper
 * @author typsoon
 */
public interface Vulnerable {
    /**
     * @return amount of remaining hitpoints
     */
    int getHitPoints();
    /**
     * Deal damage
     * @param sourceOfDamage source of received damage
     * @return {@code true} if killed | {@code false} otherwise
     */
    boolean damage(SourceOfDamage sourceOfDamage);
}
