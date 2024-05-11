package com.bksgames.game.enums;

import java.awt.*;

public enum Direction {
    LEFT, RIGHT, UP, DOWN;

    static Point getNext(Point point, Direction direction){
        switch (direction){
            case LEFT -> point.x--;
            case RIGHT -> point.x++;
            case UP -> point.y++;
            case DOWN -> point.y--;
        }
        return point;
    }
}
