package com.bksgames.game.core.updates;

import com.bksgames.game.globalClasses.enums.Displayable;
import com.bksgames.game.globalClasses.enums.UpdateIDs;
import com.bksgames.game.globalClasses.TileUpdate;

import java.awt.*;

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
    public SimpleTileUpdate(Displayable displayable, Boolean visible, Point position){
        this.displayable = displayable;
        this.visible = visible;
        this.x = position.x;
        this.y = position.y;
    }

    @Override
    public String toString() {
        return getID().name() +
                displayable.name() +
                visible +
                x + " " +
                y;
    }
}