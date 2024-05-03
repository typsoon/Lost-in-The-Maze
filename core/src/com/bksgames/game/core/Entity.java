package com.bksgames.game.core;

interface Entity extends Vulnerable, KnownPosition{
    void spawn(int x, int y, int hitPoints);
}
