package com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons.buttonContainers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;
import java.util.Collection;

public class ButtonContainer extends Table {
    protected final Collection<ImageButton> imageButtons = new ArrayList<>();

    ButtonContainer(TextureRegion region, int backgroundSize) {
        if (region != null) {
            setBackground(new TextureRegionDrawable(region));
            getBackground().setMinHeight(backgroundSize);
            getBackground().setMinWidth(backgroundSize);
        }
    }

    public Cell<ImageButton> addImageButton(ImageButton button) {
        imageButtons.add(button);
        return add(button).size(button.getWidth(), button.getHeight());
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
