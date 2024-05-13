package com.bksgames.game.views.gameScreen;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.globalClasses.enums.MoveTypes;
import com.bksgames.game.viewmodels.moves.MinionMoveListener;
import com.bksgames.game.views.gameScreen.actionButtons.ActionButtonFactory;

import javax.swing.text.TableView;
import java.awt.*;
import java.util.*;

public class LegalMoves extends Stage {
    private final Table mainTable;
    Map<MoveTypes, Table> mapping = new HashMap<>();
    private TextureAtlas atlas;
    private final MinionMoveListener minionMoveListener;

    private final ActionButtonFactory factory;

    public void displayLegalMoves(Collection<Move> legalMoves, Point minionLocation) {

//        TODO: remove code coupling
        minionMoveListener.setLocation(minionLocation);
        setLegalMoves(legalMoves);

        super.setDebugAll(true);
        super.draw();
    }

    private void setLegalMoves(Collection<Move> legalMoves) {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        boolean result = super.touchDown(screenX, screenY, pointer, button);

        displayLegalMoves(null, null);
        return result;
    }

    LegalMoves(MinionMoveListener minionMoveListener, TextureAtlas atlas, Camera gameCamera) {
//        Stage
        this.minionMoveListener = minionMoveListener;

        super.getRoot().setColor(0,0,0,0);
        super.setViewport(new ScreenViewport(gameCamera));

        mainTable = new Table();
        mainTable.align(Align.right);
        this.addActor(mainTable);

        Table arrowTable = new Table();
        arrowTable.align(Align.bottom);

        Table actionsTable = new Table();
        actionsTable.align(Align.top);

        factory = new ActionButtonFactory(minionMoveListener, atlas);

        mapping.put(MoveTypes.MOVE, arrowTable);

        mapping.put(MoveTypes.DOOR, actionsTable);
        mapping.put(MoveTypes.LASER, actionsTable);
        mapping.put(MoveTypes.SWORD, actionsTable);
        mapping.put(MoveTypes.MIRROR, actionsTable);



        arrowTable.addActor(factory.getButton(new Move(0, 0, MoveTypes.MOVE, Direction.LEFT)));
        arrowTable.addActor(factory.getButton(new Move(0, 0, MoveTypes.MOVE, Direction.RIGHT)));
        arrowTable.addActor(factory.getButton(new Move(0, 0, MoveTypes.MOVE, Direction.UP)));
        arrowTable.addActor(factory.getButton(new Move(0, 0, MoveTypes.MOVE, Direction.DOWN)));
    }
}