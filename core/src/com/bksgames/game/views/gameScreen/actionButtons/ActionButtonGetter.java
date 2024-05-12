package com.bksgames.game.views.gameScreen.actionButtons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.viewmodels.moves.MoveMaker;

import java.awt.*;

public abstract class ActionButtonGetter {
    protected final MoveMaker moveMaker;
    protected final TextureAtlas atlas;

    ActionButtonGetter(MoveMaker moveMaker, TextureAtlas atlas) {
        this.moveMaker = moveMaker;
        this.atlas = atlas;
    }

    public abstract ImageButton get(Move move, final Point minionCoords);
}
