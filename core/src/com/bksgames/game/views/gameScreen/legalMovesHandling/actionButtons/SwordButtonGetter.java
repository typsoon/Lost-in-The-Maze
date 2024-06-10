package com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bksgames.game.views.displayProperties.DisplayPropertiesSingleton;
import com.bksgames.game.common.moves.IncompleteMove;

public class SwordButtonGetter extends ActionButtonGetter{
    static public final int swordButtonSize = DisplayPropertiesSingleton.getInstance().swordButtonSize();
    private final Sound buttonClickSound;

    SwordButtonGetter(TextureAtlas atlas) {
        super(atlas);
        buttonClickSound = Gdx.audio.newSound(Gdx.files.internal("audio/swordSound.mp3"));
    }

    @Override
    public ImageButton get(IncompleteMove incompleteMove) {
//        TextureRegion region = atlas.findRegion("SwordButton");
        TextureRegion region = getArrow(incompleteMove.direction());

        region.flip(true, false);

        Sprite sprite = new Sprite(region);
        sprite.setOriginCenter();

        sprite.rotate(switch (incompleteMove.direction()) {
            case LEFT -> 0;
            case RIGHT -> 180;
            case UP -> 270;
            case DOWN -> 90;
        });

        TextureRegion rotatedRegion = new TextureRegion(sprite.getTexture());

//        ImageButton button = new ImageButton(sprite.get);
//        ImageButton button = new ImageButton(new TextureRegionDrawable(rotatedRegion));
        ImageButton button = new ImageButton(new TextureRegionDrawable(region));
        button.setSize(swordButtonSize, swordButtonSize);

        button.addCaptureListener(getTouchDownListenerForAButton(button, incompleteMove));

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                buttonClickSound.play();
            }
        });
        button.bottom().left();

        return button;
    }
}
