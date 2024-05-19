package com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
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

        switch (incompleteMove.direction()) {
            case LEFT -> button.align(Align.left);
            case RIGHT -> button.align(Align.right);
            case UP -> button.align(Align.top).align(Align.center);
            case DOWN -> button.align(Align.bottom).align(Align.center);
        }

        final int key = switch (incompleteMove.direction()){
            case LEFT -> Keys.LEFT;
            case RIGHT -> Keys.RIGHT;
            case UP -> Keys.UP;
            case DOWN -> Keys.DOWN;
        };

        button.addCaptureListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int buttonCode) {
//                This is important because of how LibGdx works
                if (!button.isVisible())
                    return false;

//                This is also due to LibGdx seeing Image as an actor
                if (event.getTarget() == button.getImage()) {
                    button.fire(new ChosenMove(incompleteMove));
                    return true;
                }
                return false;
            }

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
