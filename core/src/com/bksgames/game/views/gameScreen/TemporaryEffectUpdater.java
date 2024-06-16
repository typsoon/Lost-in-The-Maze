package com.bksgames.game.views.gameScreen;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.viewmodels.ViewFrameObserver;

import java.util.ArrayList;
import java.util.Collection;

public class TemporaryEffectUpdater implements ViewFrameObserver {
    private final int durationInFrames;
    private final TiledMapTileLayer layer;
    private final Collection<Point> points = new ArrayList<>();
    private int framesUntilClear;

    public TemporaryEffectUpdater(TiledMapTileLayer layer, int durationInFrames) {
        this.layer = layer;
        this.durationInFrames = durationInFrames;
    }

    private void clearEffect() {
        for (Point point : points) {
            layer.getCell(MazeMapFactory.maxBoardWidth+ point.x, MazeMapFactory.maxBoardHeight+point.y).setTile(null);
        }

        points.clear();
    }

    @Override
    public void addItemAtPosition(Point position) {
        points.add(position);
        if (framesUntilClear <= 0)
            framesUntilClear = durationInFrames;
    }

    @Override
    public boolean framePassed() {
        if (framesUntilClear > 0) {
            if (--framesUntilClear <= 0) {
                clearEffect();
                return false;
            }
            return true;
        }
        return false;
    }
}
