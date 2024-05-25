package com.bksgames.game.viewmodels;

import com.bksgames.game.core.utils.Point;
import com.bksgames.game.globalClasses.enums.Direction;

import java.util.*;
import java.util.List;

public class SimpleViewModel implements PlayerViewModel{
    final List<Point> minionPositions;

    EnumMap<Direction, Integer> mostDistances = new EnumMap<>(Map.of(
            Direction.UP, 0,
            Direction.DOWN, 0,
            Direction.RIGHT, 0,
            Direction.LEFT, 0
    ));
    @Override
    public int getMostDistant(Direction direction) {
        return mostDistances.get(direction);
    }

    @Override
    public void tileRevealed(Point position) {
        if(position.x>mostDistances.get(Direction.RIGHT)){
            mostDistances.put(Direction.RIGHT, position.x);
        }
        if(position.x<mostDistances.get(Direction.LEFT)){
            mostDistances.put(Direction.LEFT, position.x);
        }
        if(position.y>mostDistances.get(Direction.UP)){
            mostDistances.put(Direction.UP, position.y);
        }
        if(position.y<mostDistances.get(Direction.DOWN)){
            mostDistances.put(Direction.DOWN, position.y);
        }
    }

    @Override
    public void minionMoved(Point start, Direction direction) {
        if (!minionPositions.contains(start)) {
            return;
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
        minionPositions = new ArrayList<>(List.of(new Point[]{
                new Point(-1, 0),
                new Point(1, 0),
                new Point(0, 1),
        }));
    }
}
