package com.bksgames.game.viewmodels;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.bksgames.game.globalClasses.enums.Direction;

import java.awt.Point;
import java.util.*;
import java.util.List;

public class SimpleViewModel implements PlayerViewModel{
    List<Point> minionPositions = new ArrayList<>();
//    private final TiledMapTileLayer minionLayer;

    @Override
    public void minionMoved(Point start, Direction direction) {
        if (!minionPositions.contains(start)) {
            return;
//            throw new IllegalStateException("View model has wrong data");
        }

        for (int i = 0; i <= minionPositions.size() ; i++) {
            if (minionPositions.get(i).equals(start)) {
                Direction.getNext(minionPositions.get(i), direction);
            }
        }
    }

    @Override
    public List<Point> getMinionsPositions() {
        return minionPositions;
    }

    public SimpleViewModel(TiledMapTileLayer minionLayer) {
//        this.minionLayer = minionLayer;

        minionPositions = new ArrayList<>(List.of(new Point[]{
                new Point(-1, 0),
                new Point(1, 0),
                new Point(0, 1),
        }));
//        for (int row = 0; row < minionLayer.getHeight(); row++) {
//            for (int column = 0; column < minionLayer.getWidth(); column++) {
//                if (minionLayer.getCell(row, column) != null)
//                    minionPositions.add(minionPositions.size()+1, new Point(column, row));
//            }
//        }
    }
}
