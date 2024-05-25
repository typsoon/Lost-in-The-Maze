package com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bksgames.game.viewmodels.moves.IncompleteMove;

import java.util.ArrayList;
import java.util.Collection;

public class LaserButtonGetter extends ActionButtonGetter {
    static final int LaserArrowButtonSize = 25;

    LaserButtonGetter(TextureAtlas atlas) {
        super(atlas);
    }

    public static Table getLaserArrowButtonTable(ImageButton leftLaserArrowButton, ImageButton rightLaserArrowButton,
                                                 ImageButton upLaserArrowButton, ImageButton downLaserArrowButton, TextureRegion region) {
        LaserArrowButtonTable laserArrowButtonTable = new LaserArrowButtonTable(leftLaserArrowButton, rightLaserArrowButton, upLaserArrowButton, downLaserArrowButton);

        laserArrowButtonTable.setBackground(new TextureRegionDrawable(region));

        return laserArrowButtonTable;
    }

    @Override
    public ImageButton get(IncompleteMove incompleteMove) {
        TextureRegion region = atlas.findRegion("LaserArrowButton");

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

        button.addCaptureListener(getTouchDownListenerForAButton(button, incompleteMove));

        button.bottom().left();

        return button;
    }
}

class LaserArrowButtonTable extends Table implements Resizable {
    private final Collection<Cell<ImageButton>> laserArrowButtons = new ArrayList<>();

    LaserArrowButtonTable(ImageButton leftLaserArrowButton, ImageButton rightLaserArrowButton,
                          ImageButton upLaserArrowButton, ImageButton downLaserArrowButton) {
        add().expand().fill();

        laserArrowButtons.add(this.add(upLaserArrowButton));
        row();
        laserArrowButtons.add(this.add(leftLaserArrowButton));
        add().expand().fill();
        laserArrowButtons.add(this.add(rightLaserArrowButton));
        add().row();
        add().expand();
        laserArrowButtons.add(this.add(downLaserArrowButton));
    }

    @Override
    public void resize(float multiplier) {
        final float newSize = LaserButtonGetter.LaserArrowButtonSize * multiplier * Gdx.graphics.getHeight() / 480;

        for (Cell<ImageButton> actorCell : laserArrowButtons) {
            ImageButton tempActor = actorCell.size(newSize, newSize).getActor();
            tempActor.getImage().setSize(newSize, newSize);
        }
    }
}


class LaserArrowButton extends ImageButton {
    public LaserArrowButton(Drawable drawable) {
        super(drawable);
    }

    @Override
    public void setVisible(boolean visible) {
        if (getParent() != null) {
            getParent().setVisible(true);
        }

        super.setVisible(visible);
    }
}