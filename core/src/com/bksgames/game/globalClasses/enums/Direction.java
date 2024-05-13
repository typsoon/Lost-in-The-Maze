package com.bksgames.game.globalClasses.enums;

import java.awt.*;

public enum Direction {
    LEFT, RIGHT, UP, DOWN;

    public static void next(Point point, Direction direction){
        switch (direction){
            case LEFT -> point.x--;
            case RIGHT -> point.x++;
            case UP -> point.y++;
            case DOWN -> point.y--;
        }
    }
    public static Point getNext(Point point, Direction direction){
        switch (direction){
            case LEFT -> {
                return new Point(point.x-1,point.y);
            }
            case RIGHT -> {
                return new Point(point.x+1,point.y);
            }
            case UP -> {
                return new Point(point.x,point.y+1);
            }
            case DOWN -> {
                return new Point(point.x,point.y-1);
            }
        }
        return null;
    }
    public static Direction getDirection(Point p1, Point p2){
        if(p1.x == p2.x) {
            if(p1.y < p2.y) {
                return UP;
            }else if(p1.y > p2.y) {
                return DOWN;
            }
        }else if(p1.y == p2.y) {
            if(p1.x < p2.x) {
                return LEFT;
            } else {
                return RIGHT;
            }
        }
        return null;
    }
}
