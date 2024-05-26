package com.bksgames.game.common.utils;

import com.bksgames.game.core.utils.Point;

public enum Direction {
    LEFT(-1,0), RIGHT(1,0), UP(0,1), DOWN(0,-1);
    private final int x;
    private final int y;
    Direction(int x, int y){
        this.x=x;
        this.y=y;
    }
    public void next(Point point){
        point.x+=this.x;
        point.y+=this.y;
    }
    public Point getNext(Point point){
        return new Point(point.x+this.x,point.y+this.y);
    }
    public static Direction getDirection(Point p1, Point p2){
        if(p1.x == p2.x) {
            if(p1.y < p2.y) {
                return UP;
            }else if(p1.y > p2.y) {
                return DOWN;
            }
        }else if(p1.y == p2.y) {
            if(p1.x > p2.x) {
                return LEFT;
            } else {
                return RIGHT;
            }
        }
        return null;
    }
    public Direction getOpposite(){
        return switch (this) {
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
            case UP -> DOWN;
            case DOWN -> UP;
        };
    }
}
