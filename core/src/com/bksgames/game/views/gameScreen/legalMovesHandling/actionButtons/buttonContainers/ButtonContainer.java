package com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons.buttonContainers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons.Resizable;

import java.util.ArrayList;
import java.util.Collection;

public class ButtonContainer extends Table implements Resizable {
    private final int defaultButtonSize;
    private final Collection<ImageButton> imageButtons = new ArrayList<>();

    ButtonContainer(int defaultButtonSize, TextureRegion region) {
        setBackground(new TextureRegionDrawable(region)) ;

        this.defaultButtonSize = defaultButtonSize;
    }

    public void addImageButton(ImageButton button) {
        imageButtons.add(button);
        add(button);
    }

    @Override
    public void resize(float multiplier) {
        final float newSize = defaultButtonSize * multiplier * Gdx.graphics.getHeight() / 480;

        for (ImageButton imageButton : imageButtons) {
            getCell(imageButton).size(newSize);
            imageButton.setSize(newSize, newSize);
        }
    }

    @Override
    public void act(float delta) {
        boolean visible = false;
        for (ImageButton imageButton : imageButtons) {
            visible = visible || imageButton.isVisible();
        }
        setVisible(visible);

        super.act(delta);
    }
}
