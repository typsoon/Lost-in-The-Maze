package com.bksgames.game.core.utils;

import com.bksgames.game.common.ConfigManagerSingleton;
import com.bksgames.game.common.utils.Configuration;

/**
 * Game parameters
 * @author typsoon
 * @author riper
 */
public class Parameters {
    /**
     * On how many mirrors can {@code Laser} bend
     */
    public final int maxMirrorsBending;
    /**
     * How many {@code Wall} can {@code Player} build in single game
     */
    public final int wallBuildCount;
    /**
     * How many {@code ActionPoints} costs to place {@code Door}
     */
    public final int doorPrice;
    /**
     * How many turns must {@code Minion} wait between placing {@code Door}-s
     */
    public final int doorCooldown;
    /**
     * Starting amount of {@code Door} {@code HitPoints}
     */
    public final int doorHitPoints;
    /**
     *  Amount of {@code ActionPoints} that {@code Minion} has per turn
     */
    public final int actionsNumber;
    /**
     *  Starting amount of {@code Minion} {@code HitPoints}
     */
    public final int minionHitPoints;
    /**
     * Starting amount of {@code Nexus} {@code HitPoints}
     */
    public final int nexusHitPoints;
    /**
     * Size of {@code Board}
     */
    public final int mapSize;
    /**
     * Size of base with {@code Nexus} when creating {@code Board}
     */
    public final int baseSize;
    /**
     * How many {@code HitPoints} {@code Laser} deals
     */
    public final int laserDamage;
    /**
     * How many {@code HitPoints} {@code Sword} deals
     */
    public final int swordDamage;
    /**
     * How many {@code Minion}-s every  {@code Player} has at start
     */
    public final int minionCount;
    /**
     * Constructs {@code Parameters} with base values
     */

//    public Parameters() {
////        minionCount = 3; //max 4
////        maxMirrorsBending = 5;
////        wallBuildCount = 0;
////        doorPrice = 0;
////        doorCooldown = 2;
////        doorHitPoints = 3;
////        actionsNumber = 5;
////        minionHitPoints = 5;
////        nexusHitPoints = 20;
////        mapSize = 30;
////        baseSize = 7;
//////        TODO: think about these values
////        laserDamage = 6;
////        swordDamage = 2;
//    }

    /**
     * Constructs {@code Parameters} with custom values
     */
    public Parameters(int minionCount, int maxMirrorsBending, int wallBuildCount, int doorPrice, int doorCooldown,
                      int doorHitPoints, int actionsNumber, int minionHitPoints, int nexusHitPoints, int mapSize, int baseSize, int laserDamage, int swordDamage) {
        this.maxMirrorsBending = maxMirrorsBending;
        this.wallBuildCount = wallBuildCount;
        this.doorPrice = doorPrice;
        this.doorCooldown = doorCooldown;
        this.doorHitPoints = doorHitPoints;
        this.actionsNumber = actionsNumber;
        this.minionHitPoints = minionHitPoints;
        this.nexusHitPoints = nexusHitPoints;
        this.laserDamage = laserDamage;
        this.swordDamage = swordDamage;
        this.mapSize = mapSize;
        this.baseSize = baseSize;
        this.minionCount =  Math.min(minionCount,4);
    }
}
