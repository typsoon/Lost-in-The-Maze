package com.bksgames.game.core;

public class Parameters {
    public final int maxMirrorsBending;
    public final int wallBuildCount;
    public final int doorPrice;
    public final int doorCooldown;
    public final int doorHitPoints;
    public final int actionsNumber;
    public final int minionHitPoints;

    public Parameters() {
        maxMirrorsBending = 5;
        wallBuildCount = 0;
        doorPrice = 0;
        doorCooldown = 2;
        doorHitPoints = 3;
        actionsNumber = 5;
        minionHitPoints = 5;
    }

    public Parameters(int mMB, int wBC, int dP, int dC, int aN, int mHP) {
        maxMirrorsBending = mMB;
        wallBuildCount = wBC;
        doorPrice = dP;
        doorCooldown = dC;
        doorHitPoints = aN;
        actionsNumber = mHP;
        minionHitPoints = mHP;
    }
}
