package com.bksgames.game.core.utils;

/**
 * Game parameters
 *
 * @param maxMirrorsBending On how many mirrors can {@code Laser} bend
 * @param wallBuildCount    How many {@code Wall} can {@code Player} build in single game
 * @param doorPrice         How many {@code ActionPoints} costs to place {@code Door}
 * @param doorCooldown      How many turns must {@code Minion} wait between placing {@code Door}-s
 * @param doorHitPoints     Starting amount of {@code Door} {@code HitPoints}
 * @param actionsNumber     Amount of {@code ActionPoints} that {@code Minion} has per turn
 * @param minionHitPoints   Starting amount of {@code Minion} {@code HitPoints}
 * @param nexusHitPoints    Starting amount of {@code Nexus} {@code HitPoints}
 * @param mapSize           Size of {@code Board}
 * @param baseSize          Size of base with {@code Nexus} when creating {@code Board}
 * @param laserDamage       How many {@code HitPoints} {@code Laser} deals
 * @param swordDamage       How many {@code HitPoints} {@code Sword} deals
 * @param minionCount       How many {@code Minion}-s every  {@code Player} has at start
 * @author typsoon
 * @author riper
 */
@SuppressWarnings("unused")
public record Parameters(int minionCount, int maxMirrorsBending, int wallBuildCount, int doorPrice, int doorCooldown,
                         int doorHitPoints, int actionsNumber, int minionHitPoints, int nexusHitPoints, int mapSize,
                         int baseSize, int laserDamage, int swordDamage, int movePrice, int swordPrice, int laserPrice, int mirrorPrice) {
    /**
     * Constructs {@code Parameters} with custom values
     */

    public Parameters {

    }
}
