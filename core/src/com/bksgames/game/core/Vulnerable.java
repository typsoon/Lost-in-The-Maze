package com.bksgames.game.core;

public interface Vulnerable {

//    int setHitPoints(int hitPoints);
    int getHitPoints();

    void damage(SourceOfDamage sourceOfDamage);
}
