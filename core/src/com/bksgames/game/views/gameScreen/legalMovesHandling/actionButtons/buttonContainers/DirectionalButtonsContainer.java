package com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons.buttonContainers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.bksgames.game.views.displayProperties.DisplayPropertiesSingleton;

public class DirectionalButtonsContainer extends ButtonContainer {
    static final int backgroundSize = DisplayPropertiesSingleton.getInstance().actionButtonSize();

    public DirectionalButtonsContainer(TextureRegion region, ImageButton leftButton, ImageButton rightButton, ImageButton upButton, ImageButton downButton) {
        super(region, backgroundSize);

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
