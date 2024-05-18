package com.bksgames.game.core.tiles;

import com.bksgames.game.core.utils.Owned;
import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.globalClasses.enums.PlayerColor;

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
        SLASH(-1), BACKSLASH(+1);
        private final int x;

        Orientation(int x) {
            this.x = x;
        }
    }

    private final Orientation orientation;
    private final PlayerColor owner;

    /**
     * @param direction initial {@code Direction} of light
     * @return {@code Direction} after contact with {@code Mirror}
     */
    public Direction deflect(Direction direction) {
        return Direction.values()[(direction.ordinal() + orientation.x) % 4];
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
