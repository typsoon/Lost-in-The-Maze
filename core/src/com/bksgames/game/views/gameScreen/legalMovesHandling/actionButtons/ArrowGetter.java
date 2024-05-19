package com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.globalClasses.enums.MoveTypes;
import com.bksgames.game.viewmodels.moves.IncompleteMove;
import com.bksgames.game.viewmodels.moves.MinionMoveListener;

public class ArrowGetter extends ActionButtonGetter {
    public ArrowGetter(MinionMoveListener minionMoveListener, TextureAtlas atlas) {
        super(minionMoveListener, atlas);
    }

    private InputListener getKeyDownListener(final int key, IncompleteMove incompleteMove){
        return new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
				if (keycode == key) {
					moveListener.makeMove(new IncompleteMove(MoveTypes.MOVE, incompleteMove.direction()));
					return true;
				}
                return super.keyDown(event, keycode);
            }
        };
    }

    @Override
    public ImageButton get(IncompleteMove incompleteMove) {
        TextureRegion region = new TextureRegion();

        switch (incompleteMove.direction()) {
            case LEFT -> region = atlas.findRegion("LeftArrow");
            case RIGHT -> region = atlas.findRegion("RightArrow");
            case UP -> region = atlas.findRegion("UpArrow");
            case DOWN -> region = atlas.findRegion("DownArrow");
        }

        ImageButton button = new ImageButton(new TextureRegionDrawable(region));

        switch (incompleteMove.direction()) {
            case LEFT -> button.align(Align.left);
            case RIGHT -> button.align(Align.right);
            case UP -> button.align(Align.top).align(Align.center);
            case DOWN -> button.align(Align.bottom).align(Align.center);
        }

        switch (incompleteMove.direction()){
            case LEFT -> button.addCaptureListener(getKeyDownListener(Keys.LEFT, incompleteMove));
            case RIGHT -> button.addCaptureListener(getKeyDownListener(Keys.RIGHT, incompleteMove));
            case UP -> button.addCaptureListener(getKeyDownListener(Keys.UP, incompleteMove));
            case DOWN -> button.addCaptureListener(getKeyDownListener(Keys.DOWN, incompleteMove));
        }

        button.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                moveListener.makeMove(new IncompleteMove(MoveTypes.MOVE, incompleteMove.direction()));
            }
        });


        return button;
    }
}
