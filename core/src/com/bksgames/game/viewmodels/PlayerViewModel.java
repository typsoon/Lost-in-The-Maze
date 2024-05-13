package com.bksgames.game.viewmodels;

import com.bksgames.game.globalClasses.enums.Direction;

import java.awt.Point;
import java.util.List;


public interface PlayerViewModel {
//TODO: BIG TODO, IMPLEMENT THESE
//    int getMaxX();
//    int getMaxY();
//    int getMinX();
//    int getMinY();

    public void minionMoved(Point start, Direction direction);
    public List<Point> getMinionsPositions();
}
