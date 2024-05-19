package com.bksgames.game.viewmodels;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.globalClasses.enums.Direction;

import java.util.*;
import java.util.List;

public class SimpleViewModel implements PlayerViewModel{
    final List<Point> minionPositions;
//    private final TiledMapTileLayer minionLayer;

    @Override
    public void minionMoved(Point start, Direction direction) {
        if (!minionPositions.contains(start)) {
            return;
//            throw new IllegalStateException("View model has wrong data");
        }

        for (int i = 0; i < minionPositions.size() ; i++) {
            if (minionPositions.get(i) != null && minionPositions.get(i).equals(start)) {
                minionPositions.set(i,direction.getNext(minionPositions.get(i)));
            }
        }
    }

    @Override
    public List<Point> getMinionsPositions() {
        return minionPositions;
    }

    @Override
    public Point getMinionPos(int id) {
        if (id < 0 || id >= minionPositions.size()) {
            return null;
        }

        return minionPositions.get(id);
    }

    @Override
    public int getMinionId(Point position) {
        return minionPositions.indexOf(position);
    }

    @Override
    public boolean hasPlayableMinion(Point position) {
        return minionPositions.contains(position);
    }

    public SimpleViewModel() {
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
