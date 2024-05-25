package com.bksgames.game.core.updates;

import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.LaserUpdate;
import com.bksgames.game.common.enums.Direction;
import com.bksgames.game.common.enums.UpdateIDs;

/**
 * Simple implementation of {@code LaserUpdate}
 *
 * @author jajko
 */
public class SimpleLaserUpdate implements LaserUpdate {

    private final Direction direction;
    private final Direction deflectedDirection;
    private final Point relativePosition;

    //LaserUpdate
    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public Direction getDeflectedDirection() {
        return deflectedDirection;
    }

    @Override
    public UpdateIDs getID() {
        return UpdateIDs.LASER_UPDATE;
    }

    @Override
    public int getRelativeX() {
        return relativePosition.x;
    }

    @Override
    public int getRelativeY() {
        return relativePosition.y;
    }

    /**
     * Constructs simple {@code LaserUpdate}
     */
    public SimpleLaserUpdate(Direction direction, Direction deflectedDirection, Point relativePosition) {
        this.direction = direction;
        this.deflectedDirection = deflectedDirection;
        this.relativePosition = relativePosition.getPosition();
    }
}
