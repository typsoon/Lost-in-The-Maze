package com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bksgames.game.viewmodels.moves.IncompleteMove;

public class SwordButtonGetter extends ActionButtonGetter{
    static public final int swordButtonSize = 25;

    SwordButtonGetter(TextureAtlas atlas) {
        super(atlas);
    }

    @Override
    public ImageButton get(IncompleteMove incompleteMove) {
        TextureRegion region = atlas.findRegion("SwordButton");

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

        button.addCaptureListener(getTouchDownListenerForAButton(button, incompleteMove));

        button.bottom().left();

        return button;
    }
}
