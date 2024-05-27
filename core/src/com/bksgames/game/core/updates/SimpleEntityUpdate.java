package com.bksgames.game.core.updates;

import com.bksgames.game.common.Displayable;
import com.bksgames.game.common.updates.EntityUpdate;
import com.bksgames.game.common.updates.UpdateIDs;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.utils.*;
//TODO rename to Absolute
/**
 * Simple implementation of {@code EntityUpdate}
 *
 * @author riper
 */
public class SimpleEntityUpdate implements EntityUpdate {
    private final Direction direction;
    private final Displayable displayable;
    private final Point relativePosition;

    //EntityUpdate
    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public Displayable getDisplayable() {
        return displayable;
    }

    @Override
    public UpdateIDs getID() {
        return UpdateIDs.ENTITY_UPDATE;
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
     * Constructs simple {@code EntityUpdate}
     */
    public SimpleEntityUpdate(Direction direction, Displayable displayable, Point relativePosition) {
        this.direction = direction;
        this.displayable = displayable;
        this.relativePosition = relativePosition.getPosition();
    }
}
