package com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.bksgames.game.common.moves.ActionToken;
import com.bksgames.game.views.gameScreen.legalMovesHandling.IncompleteMove;

import java.util.HashMap;
import java.util.Map;

public class ActionButtonFactory {
    private final Map<ActionToken, ActionButtonGetter> mapping = new HashMap<>();
    private final Map<IncompleteMove, ImageButton> moveToActorMap;

    public ImageButton getButton(IncompleteMove incompleteMove) {
        ImageButton imageButton = mapping.get(incompleteMove.type()).get(incompleteMove);
        moveToActorMap.put(incompleteMove, imageButton);
        return imageButton;
    }

    public ActionButtonFactory(TextureAtlas atlas, Map<IncompleteMove, ImageButton> incompleteMoveActorMap) {
        this.moveToActorMap = incompleteMoveActorMap;

        mapping.put(ActionToken.MOVE, new ArrowGetter(atlas));
        mapping.put(ActionToken.MIRROR, new MirrorButtonGetter(atlas));
        mapping.put(ActionToken.LASER, new LaserButtonGetter(atlas));
        mapping.put(ActionToken.SWORD, new SwordButtonGetter(atlas));
        mapping.put(ActionToken.DOOR, new DoorButtonGetter(atlas));
    }
}
