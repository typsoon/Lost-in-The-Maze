package com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.viewmodels.moves.IncompleteMove;
import com.bksgames.game.views.gameScreen.legalMovesHandling.ChosenMove;

public class MirrorButtonGetter extends ActionButtonGetter {
    static final int slashButtonSize = 25;

    MirrorButtonGetter(TextureAtlas atlas) {
        super(atlas);
    }

    private InputListener getInputListenerForButton(Table table, ImageButton button, IncompleteMove move) {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int ignored) {
                if (!table.isVisible())
                    return false;

                if (event.getTarget() == button.getImage()) {
                    table.fire(new ChosenMove(move));
                    return true;
                }

                return false;
            }
        };
    }

    @Override
    public Actor get(IncompleteMove incompleteMove) {
        TextureRegion region = atlas.findRegion("MirrorButton");

        MirrorButton mirrorButton = new MirrorButton();
        mirrorButton.setBackground(new TextureRegionDrawable(region));
//        table.setVisible(true);

        TextureRegion slashMirrorTexture = atlas.findRegion("SlashMirror");
        TextureRegion backSlashMirrorTexture = atlas.findRegion("BackSlashMirror");

        ImageButton slashButton = new ImageButton(new TextureRegionDrawable(slashMirrorTexture));
        ImageButton backSlashButton = new ImageButton(new TextureRegionDrawable(backSlashMirrorTexture));

        slashButton.addCaptureListener(getInputListenerForButton(mirrorButton, slashButton,
                new IncompleteMove(incompleteMove.type(), Direction.RIGHT)));

        backSlashButton.addCaptureListener(getInputListenerForButton(mirrorButton, backSlashButton,
                new IncompleteMove(incompleteMove.type(), Direction.LEFT)));

        mirrorButton.addButtons(backSlashButton, slashButton);

        return mirrorButton;
    }
}

class MirrorButton extends Table implements ResizableActionButton {
    private Cell<ImageButton> backSlashButtonCell;
    private Cell<ImageButton> slashButtonCell;

    void addButtons(ImageButton backSlashButton, ImageButton slashButton) {
        backSlashButtonCell = add(backSlashButton);
        row();
        slashButtonCell = add(slashButton);
    }

    @Override
    public void resize(float multiplier) {
        slashButtonCell.size(MirrorButtonGetter.slashButtonSize * multiplier, MirrorButtonGetter.slashButtonSize * multiplier);
        backSlashButtonCell.size(MirrorButtonGetter.slashButtonSize * multiplier, MirrorButtonGetter.slashButtonSize * multiplier);
    }
}