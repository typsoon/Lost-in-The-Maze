package com.bksgames.game.core.utils;

import java.util.Objects;

/**
 * Point in 2d integer space
 *
 * @author riper
 */
public class Point {
    /**
     * {@code x} coordinate of {@code Point} | {@code 0} is base value
     */
    public int x;
    /**
     * {@code y} coordinate of {@code Point} | {@code 0} is base value
     */
    public int y;

    /**
     * Add another point to this
     *
     * @param point point that is added
     * @return {@code this}
     */
    public Point add(Point point) {
        this.x += point.x;
        this.y += point.y;
        return this;
    }

    /**
     * Subtract another point from this
     *
     * @param point point that is subtracted
     * @return {@code this}
     */
    public Point subtract(Point point) {
        this.x -= point.x;
        this.y -= point.y;
        return this;
    }

    /**
     * @return Copy of this point
     */
    public Point copy() {
        return new Point(this);
    }

    /**
     * Constructs point with {@code (x,y)} coordinates
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs copy of {@code point}
     */
    public Point(Point point) {
        this(point.x, point.y);
    }

    /**
     * Constructs point with {@code (0,0)} coordinates
     */
    @SuppressWarnings("unused")
    public Point() {
        this(0, 0);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
