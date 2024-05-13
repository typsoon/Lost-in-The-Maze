package com.bksgames.game.views.gameScreen.actionButtons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.globalClasses.enums.MoveTypes;
import com.bksgames.game.viewmodels.moves.IncompleteMove;
import com.bksgames.game.viewmodels.moves.MinionMoveListener;

public class ArrowGetter extends ActionButtonGetter {
    public ArrowGetter(MinionMoveListener minionMoveListener, TextureAtlas atlas) {
        super(minionMoveListener, atlas);
    }

    @Override
    public ImageButton get(Move move) {
        TextureRegion region = new TextureRegion();

        switch (move.direction()) {
            case LEFT -> region = atlas.findRegion("LeftArrow");
            case RIGHT -> region = atlas.findRegion("RightArrow");
            case UP -> region = atlas.findRegion("UpArrow");
            case DOWN -> region = atlas.findRegion("DownArrow");
        }

        ImageButton button = new ImageButton(new TextureRegionDrawable(region));

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                moveListener.makeMove(new IncompleteMove(MoveTypes.MOVE, move.direction()));
            }
        });

        return button;
    }
}
