package com.bksgames.game.core.boards;

import com.bksgames.game.core.entities.Minion;
import com.bksgames.game.core.tiles.Nexus;
import com.bksgames.game.core.tiles.Tile;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.common.PlayerColor;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Representing {@code Board}
 *
 * @author riper
 * @author jajko
 */
public interface Board {
    /**
     * @return {@code Tile} at specific coordinates {@code x} {@code y}
     */
    Tile getTile(int x, int y);

    /**
     * @return {@code Tile} at specific {@code position}
     */
    default Tile getTile(Point position) {
        return getTile(position.x, position.y);
    }

    /**
     * @return {@code Width} of {@code Board}
     */
    @SuppressWarnings("unused")
    int getWidth();


    /**
     * @return {@code Height} of {@code Board}
     */
    @SuppressWarnings("unused")
    int getHeight();

    /**
     * @return list of {@code Nexus}-es owned by {@code player}
     */
    List<Nexus> getNexus(PlayerColor player);


    /**
     * @return set of {@code Point} which are visible by {@code Minion}
     */
    Set<Point> getVisible(Minion minion); //TODO Move to VisionManager

    /**
     * @return set of {@code Point} which are visible by {@code Nexus}
     */
    Set<Point> getNexusesVision(PlayerColor player); //TODO Move to VisionManager

    /**
     * @return list of {@code Point} which are visible when looking from {@code point} in {@code direction}
     */
    List<Point> getLineOfSight(Point point, Direction direction, Collection<PlayerColor> canSee); //TODO Move to VisionManager


}
