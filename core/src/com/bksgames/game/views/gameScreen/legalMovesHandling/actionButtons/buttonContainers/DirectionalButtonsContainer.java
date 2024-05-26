package com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons.buttonContainers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

public class DirectionalButtonsContainer extends ButtonContainer {
    public DirectionalButtonsContainer(int defaultButtonSize, TextureRegion region, ImageButton leftButton, ImageButton rightButton, ImageButton upButton, ImageButton downButton) {
        super(defaultButtonSize, region);

        add().expand();
        addImageButton(upButton);
        row();
        addImageButton(leftButton);
        add().expand();
        addImageButton(rightButton);
        add().row();
        add().expand();
        addImageButton(downButton);
    }
}
