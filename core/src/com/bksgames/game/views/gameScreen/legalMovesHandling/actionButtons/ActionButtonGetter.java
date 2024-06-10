package com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bksgames.game.common.moves.IncompleteMove;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.views.gameScreen.legalMovesHandling.ChosenMove;

public abstract class ActionButtonGetter {
    protected final TextureAtlas atlas;

    ActionButtonGetter(TextureAtlas atlas) {
        this.atlas = atlas;
    }

    protected static InputListener getTouchDownListenerForAButton(ImageButton button, IncompleteMove incompleteMove) {
        return new ClickListener() {
            @Override
            public synchronized boolean touchDown(InputEvent event, float x, float y, int pointer, int whichButton) {
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
        };
    }

    public TextureRegion getArrow(Direction direction) {
        return atlas.findRegion(switch(direction) {
            case LEFT -> "RightArrow";
            case RIGHT -> "LeftArrow";
            case UP -> "UpArrow";
            case DOWN -> "DownArrow";
        });
    }

    public abstract ImageButton get(IncompleteMove incompleteMove);
}
