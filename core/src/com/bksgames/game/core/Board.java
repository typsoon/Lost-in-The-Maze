package com.bksgames.game.core;

public interface Board {

    Tile getTile(int x, int y);
    int getWidth();
    int getHeight();
}
