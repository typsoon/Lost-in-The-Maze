package com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bksgames.game.views.displayProperties.DisplayPropertiesSingleton;
import com.bksgames.game.common.moves.IncompleteMove;

public class MirrorButtonGetter extends ActionButtonGetter {
    static final int mirrorButtonSize = DisplayPropertiesSingleton.getInstance().mirrorButtonSize();
    private final Sound buttonClickSound;

    MirrorButtonGetter(TextureAtlas atlas) {
        super(atlas);
        buttonClickSound = Gdx.audio.newSound(Gdx.files.internal("assets/audio/mirrorSound.mp3"));
    }

    @Override
    public ImageButton get(IncompleteMove incompleteMove) {

        TextureRegion buttonTexture = switch (incompleteMove.direction()) {
            case RIGHT -> atlas.findRegion("SlashMirror");
            case LEFT -> atlas.findRegion("BackSlashMirror");

            default -> throw new IllegalStateException("Unexpected value: " + incompleteMove.direction() + " as a mirror acceptMove direction");
        };

        ImageButton button = new ImageButton(new TextureRegionDrawable(buttonTexture));
        button.setSize(mirrorButtonSize, mirrorButtonSize);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                buttonClickSound.play();
            }
        });

        button.bottom().left();

        button.addCaptureListener(getTouchDownListenerForAButton(button, incompleteMove));

        return button;
    }
}