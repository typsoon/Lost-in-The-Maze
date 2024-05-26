package com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bksgames.game.viewmodels.moves.IncompleteMove;

public class MirrorButtonGetter extends ActionButtonGetter {
    static final int slashButtonSize = 30;

    MirrorButtonGetter(TextureAtlas atlas) {
        super(atlas);
    }

    public static Table getMirrorButtonTable(ImageButton backSlashButton, ImageButton slashButton, TextureRegion region) {
        MirrorButtonTable mirrorButtonTable = new MirrorButtonTable(backSlashButton, slashButton);
        mirrorButtonTable.setBackground(new TextureRegionDrawable(region));

        return mirrorButtonTable;
    }

    @Override
    public ImageButton get(IncompleteMove incompleteMove) {

        TextureRegion buttonTexture = switch (incompleteMove.direction()) {
            case RIGHT -> atlas.findRegion("SlashMirror");
            case LEFT -> atlas.findRegion("BackSlashMirror");

            default -> throw new IllegalStateException("Unexpected value: " + incompleteMove.direction() + " as a mirror move direction");
        };

        MirrorButton button = new MirrorButton(new TextureRegionDrawable(buttonTexture));
        button.bottom().left();

        button.addCaptureListener(getTouchDownListenerForAButton(button, incompleteMove));

        return button;
    }
}

class MirrorButtonTable extends Table implements Resizable {
    private final Cell<ImageButton> backSlashButtonCell;
    private final Cell<ImageButton> slashButtonCell;

    MirrorButtonTable(ImageButton backSlashButton, ImageButton slashButton) {
        backSlashButtonCell = add(backSlashButton);
        row();
        slashButtonCell = add(slashButton);
    }

    @Override
    public void resize(final float multiplier) {
//        TODO: Remove magic value from here
        final float newSize = MirrorButtonGetter.slashButtonSize * multiplier * Gdx.graphics.getHeight()/480;

        ImageButton tempActor = slashButtonCell.size(newSize, newSize).getActor();
//        tempActor.setSize(newSize, newSize);
        tempActor.getImage().setSize(newSize, newSize);

        tempActor = backSlashButtonCell.size(newSize, newSize).getActor();
//        tempActor.setSize(newSize, newSize);
        tempActor.getImage().setSize(newSize, newSize);
    }
}

class MirrorButton extends ImageButton {
    public MirrorButton(Drawable imageUp) {
        super(imageUp);
    }

    @Override
    public void setVisible(boolean visible) {
        if (getParent() != null) {
            getParent().setVisible(visible);
        }
//        else throw new IllegalStateException("Mirror button parent should not be null");

        super.setVisible(visible);
    }
}