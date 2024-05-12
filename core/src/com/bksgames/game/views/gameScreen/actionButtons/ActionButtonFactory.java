package com.bksgames.game.views.gameScreen.actionButtons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.bksgames.game.core.actionsHandlers.ActionHandler;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.globalClasses.enums.MoveTypes;
import com.bksgames.game.viewmodels.moves.MoveMaker;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ActionButtonFactory {
    private final Map<MoveTypes, ActionButtonGetter> mapping = new HashMap<>();

    public ImageButton getButton(Move move, final Point minionCoords) {
        return mapping.get(move.type()).get(move, minionCoords);
    };

    public ActionButtonFactory(MoveMaker moveMaker, TextureAtlas atlas) {
        mapping.put(MoveTypes.MOVE, new ArrowButtonGetter(moveMaker, atlas));
    }
}
