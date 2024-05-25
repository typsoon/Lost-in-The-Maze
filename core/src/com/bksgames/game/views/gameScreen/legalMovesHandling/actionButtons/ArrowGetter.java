package com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bksgames.game.viewmodels.moves.IncompleteMove;
import com.bksgames.game.views.gameScreen.legalMovesHandling.ChosenMove;

public class ArrowGetter extends ActionButtonGetter {
    public ArrowGetter(TextureAtlas atlas) {
        super(atlas);
    }

    @Override
    public ImageButton get(IncompleteMove incompleteMove) {
        String textureName = switch (incompleteMove.direction()) {
            case LEFT -> "LeftArrow";
            case RIGHT -> "RightArrow";
            case UP -> "UpArrow";
            case DOWN -> "DownArrow";
        };
        TextureRegion region = atlas.findRegion(textureName);
//        switch (incompleteMove.direction()) {
//            case LEFT -> region = atlas.findRegion("LeftArrow");
//            case RIGHT -> region = atlas.findRegion("RightArrow");
//            case UP -> region = atlas.findRegion("UpArrow");
//            case DOWN -> region = atlas.findRegion("DownArrow");
//        }

        ImageButton button = new ImageButton(new TextureRegionDrawable(region));
        button.setName(textureName);
        button.getImage().setFillParent(true);

        final int key = switch (incompleteMove.direction()){
            case LEFT -> Keys.LEFT;
            case RIGHT -> Keys.RIGHT;
            case UP -> Keys.UP;
            case DOWN -> Keys.DOWN;
        };

        button.bottom().left();

        button.addCaptureListener(getTouchDownListenerForAButton(button, incompleteMove));
        button.addCaptureListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == key) {
                    button.fire(new ChosenMove(incompleteMove));
                    return true;
                }

                return false;
            }
        });


        return button;
    }
}
