package com.bksgames.game.viewmodels;

import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.viewmodels.laserHandling.ViewLaserHandler;

import java.util.*;
import java.util.List;

public class SimpleViewModel implements PlayerViewModel{
    final List<Point> minionPositions;
    private final ViewLaserHandler viewLaserHandler;

    @Override
    public void laserFired(Point position) {
        viewLaserHandler.addLaserAtPosition(position);
    }

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
    public void minionKilled(Point position) {
        if (!minionPositions.contains(position)) {
            throw new IllegalStateException("No minion on position" + position);
        }

//        this means a minion is dead
        minionPositions.set(minionPositions.indexOf(position),new Point(0, 0));
    }

    @Override
    public void minionSpawned(Point position) {

        for (int i = 0 ; i < minionPositions.size() ; i++) {
            if (minionPositions.get(i).equals(new Point(0, 0))) {
                minionPositions.set(i,position);
                return;
            }
        }

        minionPositions.add(position);
    }

    @Override
    public int getMinionId(Point position) {
        return minionPositions.indexOf(position);
    }

    @Override
    public boolean hasPlayableMinion(Point position) {
        return minionPositions.contains(position);
    }

    public SimpleViewModel(ViewLaserHandler viewLaserHandler) {
        this.viewLaserHandler = viewLaserHandler;

        minionPositions = new ArrayList<>();

//        TODO: this data should be provided in updates
        minionSpawned(new Point(-1, 0));
        minionSpawned(new Point(1, 0));
        minionSpawned(new Point(0, 1));
    }
}
