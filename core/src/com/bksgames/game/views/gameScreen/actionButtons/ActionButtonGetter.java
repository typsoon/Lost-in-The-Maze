package com.bksgames.game.views.gameScreen.actionButtons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.viewmodels.moves.MinionMoveListener;

public abstract class ActionButtonGetter {
    protected final MinionMoveListener moveListener;
    protected final TextureAtlas atlas;

    ActionButtonGetter(MinionMoveListener moveListener, TextureAtlas atlas) {
        this.moveListener = moveListener;
        this.atlas = atlas;
    }

    public abstract ImageButton get(Move move);
}
