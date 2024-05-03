package com.bksgames.game.core;

public abstract class BoardState {
    final int width, height;

    public final int getWidth() {return width;};
    public final int getHeight() {return height;};

    protected Tile[][] tiles;

    protected BoardState(int width, int height) {
        this.width = width;
        this.height = height;

//    We have to add edges
        tiles = new Tile[width+2][height+2];

//        Generate board here
    }
}
