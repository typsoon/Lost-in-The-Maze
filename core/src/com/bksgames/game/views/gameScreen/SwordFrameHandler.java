package com.bksgames.game.views.gameScreen;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.viewmodels.ViewFrameObserver;

import java.util.ArrayList;
import java.util.Collection;

public class SwordFrameHandler implements ViewFrameObserver {
    private static final int slayDurationInFrames = 10;
    private final TiledMapTileLayer swordLayer;
    private final Collection<Point> swordPoints = new ArrayList<>();
    private int framesUntilClear;

    public SwordFrameHandler(TiledMapTileLayer swordLayer) {
        this.swordLayer = swordLayer;
    }

    private void clearLaser() {
        for (Point point : swordPoints) {
            swordLayer.getCell(MazeMapFactory.maxBoardWidth+ point.x, MazeMapFactory.maxBoardHeight+point.y).setTile(null);
        }

        swordPoints.clear();
    }

    @Override
    public void addItemAtPosition(Point position) {
        swordPoints.add(position);
        if (framesUntilClear <= 0)
            framesUntilClear = slayDurationInFrames;
    }

    @Override
    public boolean framePassed() {
        if (framesUntilClear > 0) {
            if (--framesUntilClear <= 0) {
                clearLaser();
                return false;
            }
            return true;
        }
        return false;
    }
}
