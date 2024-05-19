package com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bksgames.game.viewmodels.moves.IncompleteMove;

public abstract class ActionButtonGetter {
    protected final TextureAtlas atlas;

    ActionButtonGetter(TextureAtlas atlas) {
        this.atlas = atlas;
    }

    public abstract Actor get(IncompleteMove incompleteMove);
}
