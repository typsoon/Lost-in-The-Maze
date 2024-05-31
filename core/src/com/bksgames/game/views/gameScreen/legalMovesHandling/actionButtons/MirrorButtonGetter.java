package com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bksgames.game.views.gameScreen.legalMovesHandling.IncompleteMove;

public class MirrorButtonGetter extends ActionButtonGetter {
    static final int mirrorButtonSize = 25;

    MirrorButtonGetter(TextureAtlas atlas) {
        super(atlas);
    }

    @Override
    public ImageButton get(IncompleteMove incompleteMove) {

        TextureRegion buttonTexture = switch (incompleteMove.direction()) {
            case RIGHT -> atlas.findRegion("SlashMirror");
            case LEFT -> atlas.findRegion("BackSlashMirror");

            default -> throw new IllegalStateException("Unexpected value: " + incompleteMove.direction() + " as a mirror move direction");
        };

        ImageButton button = new ImageButton(new TextureRegionDrawable(buttonTexture));
        button.setSize(mirrorButtonSize, mirrorButtonSize);

        button.bottom().left();

        button.addCaptureListener(getTouchDownListenerForAButton(button, incompleteMove));

        return button;
    }
}