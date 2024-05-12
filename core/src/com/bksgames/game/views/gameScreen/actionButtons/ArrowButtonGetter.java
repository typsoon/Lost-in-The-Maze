package com.bksgames.game.views.gameScreen.actionButtons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.globalClasses.enums.MoveTypes;
import com.bksgames.game.viewmodels.moves.MoveMaker;

import java.awt.*;

public class ArrowButtonGetter extends ActionButtonGetter {
    public ArrowButtonGetter(MoveMaker move, TextureAtlas atlas) {
        super(move, atlas);
    }

    @Override
    public ImageButton get(Move move, Point minionCoords) {
        TextureRegion region = new TextureRegion();

        switch (move.direction()) {
            case LEFT -> region = atlas.findRegion("LeftArrow");
            case RIGHT -> region = atlas.findRegion("RightArrow");
            case UP -> region = atlas.findRegion("UpArrow");
            case DOWN -> region = atlas.findRegion("DownArrow");
        }

        ImageButton button = new ImageButton(new TextureRegionDrawable(region));

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                moveMaker.makeMove(new Move(minionCoords.x, minionCoords.y, MoveTypes.MOVE, move.direction()));
            }
        });

        return button;
    }
}
