package com.bksgames.game.viewmodels;

import com.bksgames.game.core.utils.Point;
import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.views.gameScreen.laserHandling.ViewLaserHandler;

import java.util.*;
import java.util.List;

public class SimpleViewModel implements PlayerViewModel{
    final List<Point> minionPositions;
    private final ViewLaserHandler viewLaserHandler;

    @Override
    public void laserFired(Point position) {
        viewLaserHandler.addLaserAtPosition(position);
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

    public SimpleViewModel(ViewLaserHandler viewLaserHandler) {
        this.viewLaserHandler = viewLaserHandler;
        minionPositions = new ArrayList<>(List.of(new Point[]{
                new Point(-1, 0),
                new Point(1, 0),
                new Point(0, 1),
        }));
    }
}
