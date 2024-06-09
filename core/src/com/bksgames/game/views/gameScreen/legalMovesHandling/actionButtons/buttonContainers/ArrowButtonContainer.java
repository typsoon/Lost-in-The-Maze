package com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons.buttonContainers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.bksgames.game.views.displayProperties.DisplayPropertiesSingleton;

public class ArrowButtonContainer extends ButtonContainer {
    static private final int distanceToNextButton = DisplayPropertiesSingleton.getInstance().distanceToAdjacentButton();

    public ArrowButtonContainer(TextureRegion region, ImageButton leftArrowButton, ImageButton rightArrowButton, ImageButton upArrowButton, ImageButton downArrowButton) {
        super(region, 0);

        add().expand().fill();

        addImageButton(upArrowButton).padBottom(distanceToNextButton).padRight(distanceToNextButton);
        row();
        addImageButton(leftArrowButton).padRight(distanceToNextButton);
        addImageButton(downArrowButton).padRight(distanceToNextButton);
        addImageButton(rightArrowButton);

        addCaptureListener(event -> {
            for (ImageButton imageButton : imageButtons) {
                if (imageButton.isVisible() && imageButton.notify(event, true))
                    return true;
            }

            return false;
        }
        );
    }
}
