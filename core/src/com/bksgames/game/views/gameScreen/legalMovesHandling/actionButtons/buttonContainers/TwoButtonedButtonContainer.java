package com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons.buttonContainers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

public class TwoButtonedButtonContainer extends ButtonContainer {
    public TwoButtonedButtonContainer(int defaultButtonSize, TextureRegion region, ImageButton upButton, ImageButton downButton) {
        super(defaultButtonSize, region);

        addImageButton(upButton);
        row();
        addImageButton(downButton);
    }
}
