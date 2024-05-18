package com.bksgames.game.core.utils;

public class Parameters {
    public final int maxMirrorsBending;
    public final int wallBuildCount;
    public final int doorPrice;
    public final int doorCooldown;
    public final int doorHitPoints;
    public final int actionsNumber;
    public final int minionHitPoints;
    public final int nexusHitPoints;
    public final int mapSize;

    public final int laserDamage;
    public final int swordDamage;

    public Parameters() {
        maxMirrorsBending = 5;
        wallBuildCount = 0;
        doorPrice = 0;
        doorCooldown = 2;
        doorHitPoints = 3;
        actionsNumber = 5;
        minionHitPoints = 5;
        nexusHitPoints = 20;
        mapSize = 300;
//        TODO: think about these values
        laserDamage = 6;
        swordDamage = 2;
    }

    public Parameters(int maxMirrorsBending, int wallBuildCount, int doorPrice, int doorHitPoints, int doorCooldown, int actionsNumber, int minionHitPoints, int nexusHitPoints
    , int laserDamage, int swordDamage, int mapSize) {
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
    }
}
