package com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.viewmodels.moves.IncompleteMove;

public class MirrorButtonGetter extends ActionButtonGetter {
    public static final int slashButtonSize = 30;

    MirrorButtonGetter(TextureAtlas atlas) {
        super(atlas);
    }

    @Override
    public ImageButton get(IncompleteMove incompleteMove) {

        TextureRegion buttonTexture = switch (incompleteMove.direction()) {
            case Direction.RIGHT -> atlas.findRegion("SlashMirror");
            case Direction.LEFT -> atlas.findRegion("BackSlashMirror");

            default -> throw new IllegalStateException("Unexpected value: " + incompleteMove.direction() + " as a mirror move direction");
        };

        ImageButton button = new ImageButton(new TextureRegionDrawable(buttonTexture));
        button.bottom().left();

        button.addCaptureListener(getTouchDownListenerForAButton(button, incompleteMove));

        return button;
    }
}