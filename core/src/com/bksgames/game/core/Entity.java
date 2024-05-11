package com.bksgames.game.core;

public interface Entity extends Vulnerable, KnownPosition{
    void spawn(int x, int y, int hitPoints);
}
