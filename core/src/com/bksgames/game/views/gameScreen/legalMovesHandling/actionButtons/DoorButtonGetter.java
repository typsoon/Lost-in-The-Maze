package com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bksgames.game.views.displayProperties.DisplayPropertiesSingleton;
import com.bksgames.game.common.moves.IncompleteMove;

public class DoorButtonGetter extends ActionButtonGetter {
    static public final int DoorButtonSize = DisplayPropertiesSingleton.getInstance().doorButtonSize();

    DoorButtonGetter(TextureAtlas atlas) {
        super(atlas);
    }

    @Override
    public ImageButton get(IncompleteMove incompleteMove) {
        TextureRegion region = atlas.findRegion("DoorButton");

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
        button.setSize(DoorButtonSize, DoorButtonSize);

        button.addCaptureListener(getTouchDownListenerForAButton(button, incompleteMove));

        button.bottom().left();

        return button;
    }
}
