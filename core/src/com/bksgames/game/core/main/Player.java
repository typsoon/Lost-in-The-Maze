package com.bksgames.game.core.main;

import com.bksgames.game.core.entities.Minion;
import com.bksgames.game.core.utils.Point;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Representing {@code Player}
 * @author riper
 * @author typsoon
 */
public class Player {


    private final Point mainNexus;
    private final Collection<Minion> minions = new ArrayList<>();
    private final Set<Point> visibleTiles = new HashSet<>();

    /**
     * make necessary thing before next turn starts
     */
    public void nextTurn(){
        for(Minion m : minions){
            m.nextTurn();
        }
    }
    /**
     * @return coordinates of main {@code Nexus}
     */
    public Point getMainNexus() {
        return mainNexus;
    }
    /**
     *
     * @param coordinates absolute
     * @return {@code coordinates} but relative to position of {@code mainNexus}
     */
    public Point getRelativeCoordinates(Point coordinates) {
        return coordinates.copy().subtract(mainNexus);
    }

    /**
     * @param coordinates relative to {@code mainNexus}
     * @return {@code coordinates} but absolute
     */
    public Point getAbsoluteCoordinates(Point coordinates) {
        return coordinates.copy().add(mainNexus);
    }

    /**
     * Add {@code minion} to {@code Player}
     */
    void addMinion(Minion minion) {
        minions.add(minion);
    }

    /**
     * @return {@code Minion} on {@code position} if {@code Player} has one | {@code null} otherwise
     */
    public Minion getMinion(Point position) {
        for (Minion minion : minions) {
            if (minion.getHitPoints()>0 && minion.getPosition().equals(position)) {
                return minion;
            }
        }
        return null;
    }

    /**
     * @return Collection of {@code Player} {@code Minion}-s
     */
    public Collection<Minion> getMinions() {
        return minions;
    }
    /**
     * @return {@code True} if {@code Player} can see this {@code point} <br>
     * {@code False} otherwise
     */
    public boolean isVisible(Point point) {
        return visibleTiles.contains(point);
    }
    public boolean isVisible(int x,int y) {
        return visibleTiles.contains(new Point(x, y));
    }

    /**
     * @param updates set of {@code Point}-s that {@code Player} can currently see
     * @return difference between previous vision and actual of {@code Player}
     */
    Set<Point> updateVisibleTiles(Set<Point> updates) {
        Set<Point> diff = new HashSet<>();
        for (Point point : updates) {
            if (!visibleTiles.contains(point)) {
                diff.add(point);
            }
        }
        for (Point point : visibleTiles) {
            if (!updates.contains(point)) {
                diff.add(point);
            }
        }
        visibleTiles.clear();
        visibleTiles.addAll(updates);
        return diff;
    }

    /**
     * Constructs a {@code Player}
     */
    Player(Point mainNexus) {
        this.mainNexus = mainNexus.copy();
    }
}
