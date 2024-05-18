package com.bksgames.game.core.utils;
/**
 * Objects have position represented by {@code Point}
 * @author typsoon
 * @author riper
 */
public interface KnownPosition {
    /**
     * @return position of object
     */
    Point getPosition();
    /**
     * @return {@code x} coordinate of position of object
     */
    default int getX(){return this.getPosition().x;}
    /**
     * @return {@code y} coordinate of position of object
     */
    default int getY(){return this.getPosition().y;}
}
