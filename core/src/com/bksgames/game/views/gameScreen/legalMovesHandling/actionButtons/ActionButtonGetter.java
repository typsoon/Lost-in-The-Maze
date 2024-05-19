package com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bksgames.game.viewmodels.moves.IncompleteMove;
import com.bksgames.game.viewmodels.moves.MinionMoveListener;

public abstract class ActionButtonGetter {
    protected final MinionMoveListener moveListener;
    protected final TextureAtlas atlas;

    ActionButtonGetter(MinionMoveListener moveListener, TextureAtlas atlas) {
        this.moveListener = moveListener;
        this.atlas = atlas;
    }

    public abstract Actor get(IncompleteMove incompleteMove);
}
