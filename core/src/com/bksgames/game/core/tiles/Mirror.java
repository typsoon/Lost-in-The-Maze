package com.bksgames.game.core.tiles;

import com.bksgames.game.core.utils.Owned;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.common.PlayerColor;

/**
 * Representing {@code Mirror}
 *
 * @author jajko
 * @author riper
 */
public record Mirror(Orientation orientation, PlayerColor owner) implements Owned {

    /**
     * {@code Orientation} of {@code Mirror}<br>
     * {@code SLASH} - {@code /}<br>
     * {@code BACKSLASH} - {@code \}
     */
    public enum Orientation {
        SLASH, BACKSLASH
    }

    /**
     * @return {@code Orientation} of {@code Mirror}
     */
    @Override
    public Orientation orientation() {
        return orientation;
    }

    /**
     * @param direction initial {@code Direction} of light
     * @return {@code Direction} after contact with {@code Mirror}
     */
    public Direction deflect(Direction direction) {
        if (orientation == Orientation.SLASH) {
            return switch (direction) {
                case UP -> Direction.RIGHT;
                case DOWN -> Direction.LEFT;
                case LEFT -> Direction.DOWN;
                case RIGHT -> Direction.UP;
            };
        } else {
            return switch (direction) {
                case UP -> Direction.LEFT;
                case DOWN -> Direction.RIGHT;
                case LEFT -> Direction.UP;
                case RIGHT -> Direction.DOWN;
            };
        }
    }
    //mirrors need to be distinct
    @Override
    public boolean equals(Object obj) {
        return obj==this;
    }

    /**
     * Constructs a {@code Mirror}
     */
    public Mirror {
    }

}
