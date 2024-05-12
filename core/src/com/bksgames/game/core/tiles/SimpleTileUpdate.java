package com.bksgames.game.core.tiles;

import com.bksgames.game.core.Player;
import com.bksgames.game.enums.Displayable;
import com.bksgames.game.enums.PlayerColor;
import com.bksgames.game.enums.UpdateIDs;
import com.bksgames.game.updateData.TileUpdate;

public class SimpleTileUpdate implements TileUpdate {
    private Displayable displayable;
    private Boolean visible;
    private int x;
    private int y;
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
        return x;
    }

    @Override
    public int getRelativeY() {
        return y;
    }
    public SimpleTileUpdate(Displayable displayable, Boolean visible,int x,int y){
        this.displayable = displayable;
        this.visible = visible;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return getID().name() +
                displayable.name() +
                visible +
                x +
                y;
    }
}
