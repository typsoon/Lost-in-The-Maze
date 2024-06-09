package com.bksgames.game.views.gameScreen;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.viewmodels.laserHandling.ViewLaserHandler;
import com.bksgames.game.views.displayProperties.DisplayPropertiesSingleton;

import java.util.ArrayList;
import java.util.Collection;

public class SimpleLaserHandler implements ViewLaserHandler {
    private static final int laserDurationInFrames = DisplayPropertiesSingleton.getInstance().laserDurationInFrames();
    private final TiledMapTileLayer laserLayer;
    private final Collection<Point> laserPoints = new ArrayList<>();
    private int framesUntilClear;

    public SimpleLaserHandler(TiledMapTileLayer laserLayer) {
        this.laserLayer = laserLayer;
    }

    private void clearLaser() {
        for (Point point : laserPoints) {
            laserLayer.getCell(MazeMapFactory.maxBoardWidth+ point.x, MazeMapFactory.maxBoardHeight+point.y).setTile(null);
        }

        laserPoints.clear();
    }

    @Override
    public void addLaserAtPosition(Point position) {
        laserPoints.add(position);
        if (framesUntilClear <= 0)
            framesUntilClear = laserDurationInFrames;
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
