package com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.bksgames.game.globalClasses.enums.MoveTypes;
import com.bksgames.game.viewmodels.moves.IncompleteMove;
import com.bksgames.game.viewmodels.moves.MinionMoveListener;

import java.util.HashMap;
import java.util.Map;

public class ActionButtonFactory {
    private final Map<MoveTypes, ActionButtonGetter> mapping = new HashMap<>();

    public ImageButton getButton(IncompleteMove incompleteMove) {
        return mapping.get(incompleteMove.type()).get(incompleteMove);
    }

    public ActionButtonFactory(MinionMoveListener moveListener, TextureAtlas atlas) {
        mapping.put(MoveTypes.MOVE, new ArrowGetter(moveListener, atlas));
    }
}
