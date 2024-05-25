package com.bksgames.game.core.updates;

import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.TileUpdate;
import com.bksgames.game.common.enums.Displayable;
import com.bksgames.game.common.enums.UpdateIDs;

/**
 * Simple implementation of {@code TileUpdate}
 *
 * @author riper
 */
public class SimpleTileUpdate implements TileUpdate {
    private final Displayable displayable;
    private final Boolean visible;
    private final Point relativePosition;

    //TileUpdate
    @Override
    public Displayable whatToDisplay() {
        return displayable;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public UpdateIDs getID() {
        return UpdateIDs.TILE_UPDATE;
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
     * Constructs simple {@code TileUpdate}
     */
    public SimpleTileUpdate(Displayable displayable, Boolean visible, Point relativePosition) {
        this.displayable = displayable;
        this.visible = visible;
        this.relativePosition = relativePosition.getPosition();
    }

}
