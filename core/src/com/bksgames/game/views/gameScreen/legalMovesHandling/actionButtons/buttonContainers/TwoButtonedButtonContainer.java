package com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons.buttonContainers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.bksgames.game.views.displayProperties.DisplayPropertiesSingleton;

public class TwoButtonedButtonContainer extends ButtonContainer {
    static final int backgroundSize = DisplayPropertiesSingleton.getInstance().actionButtonSize();

    public TwoButtonedButtonContainer(TextureRegion region, ImageButton upButton, ImageButton downButton) {
        super(region, backgroundSize);

        addImageButton(upButton).center();
        row();

        addImageButton(downButton).center();
    }
}
