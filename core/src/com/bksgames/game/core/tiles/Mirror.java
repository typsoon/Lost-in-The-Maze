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
public class Mirror implements Owned {
    /**
     * {@code Orientation} of {@code Mirror}<br>
     * {@code SLASH} - {@code /}<br>
     * {@code BACKSLASH} - {@code \}
     */
    public enum Orientation {
        SLASH, BACKSLASH
    }

    private final Orientation orientation;
    private final PlayerColor owner;

    /**
     * @return {@code Orientation} of {@code Mirror}
     */
    public Orientation getOrientation() {
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
        }
       else{
            return switch (direction) {
                case UP -> Direction.LEFT;
                case DOWN -> Direction.RIGHT;
                case LEFT -> Direction.UP;
                case RIGHT -> Direction.DOWN;
            };
        }
    }

    //Owned
    @Override
    public PlayerColor getOwner() {
        return owner;
    }

    /**
     * Constructs a {@code Mirror}
     */
    public Mirror(Orientation orientation, PlayerColor owner) {
        this.orientation = orientation;
        this.owner = owner;
    }

}
