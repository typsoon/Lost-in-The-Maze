package com.bksgames.game.views.gameScreen;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.globalClasses.enums.MoveTypes;
import com.bksgames.game.viewmodels.moves.MinionMoveListener;
import com.bksgames.game.views.gameScreen.actionButtons.ActionButtonFactory;

import java.awt.*;
import java.util.*;

public class LegalMoves extends Stage {
    private final Table mainTable;
    Map<MoveTypes, Table> mapping = new HashMap<>();
//    private TextureAtlas atlas;
    private final MinionMoveListener minionMoveListener;

    private final Table arrowTable;
    private final Table actionsTable;

    private final ActionButtonFactory factory;

    public void displayLegalMoves(Collection<Move> legalMoves, Point minionLocation) {

//        TODO: remove code coupling
        minionMoveListener.setLocation(minionLocation);
        setLegalMoves(legalMoves);
        mainTable.setVisible(true);
    }

    private void setLegalMoves(Collection<Move> legalMoves) {
        actionsTable.setVisible(true);
        actionsTable.setVisible(true);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (!mainTable.isVisible()) {
            return false;
        }

        boolean result = super.touchDown(screenX, screenY, pointer, button);

        deactivateLegalMoves();

        return result;
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (!mainTable.isVisible()) {
            return false;
        }

        boolean result = super.keyDown(keyCode);

        deactivateLegalMoves();

        for (Actor actor : arrowTable.getChildren()) {
            if (!actor.isVisible())
                continue;

            for (EventListener listener : actor.getListeners()) {
                if (!(listener instanceof InputListener inputListener)) {
                    throw new IllegalStateException("Illegal state");
                }

                if(inputListener.keyDown(new InputEvent(), keyCode))
                    return true;
            }
        }

        return result;
    }

    public void deactivateLegalMoves() {
        mainTable.setVisible(false);
    }

    LegalMoves(MinionMoveListener minionMoveListener, TextureAtlas atlas, Camera gameCamera) {
//        Stage
        super(new ScreenViewport(gameCamera));
        super.getViewport().setCamera(gameCamera);

        this.minionMoveListener = minionMoveListener;

        super.getRoot().setColor(0,0,0,1);
//        super.setViewport(new ScreenViewport(gameCamera));
        super.getBatch().setProjectionMatrix(gameCamera.combined);

//        super.setDebugAll(true);

        factory = new ActionButtonFactory(minionMoveListener, atlas);

        mainTable = new Table();
        mainTable.setPosition((MazeMapFactory.maxBoardWidth+ 10)*MazeMapFactory.tilePixelSize, (MazeMapFactory.maxBoardHeight)*MazeMapFactory.tilePixelSize);
        mainTable.align(Align.right);
        this.addActor(mainTable);
        mainTable.setVisible(false);

        arrowTable = new Table();
        arrowTable.align(Align.bottom);

        actionsTable = new Table();
        actionsTable.align(Align.top);

        mapping.put(MoveTypes.MOVE, arrowTable);

        mapping.put(MoveTypes.DOOR, actionsTable);
        mapping.put(MoveTypes.LASER, actionsTable);
        mapping.put(MoveTypes.SWORD, actionsTable);
        mapping.put(MoveTypes.MIRROR, actionsTable);

        arrowTable.add(factory.getButton(new Move(0, 0, MoveTypes.MOVE, Direction.UP)));

        arrowTable.row();

        arrowTable.add(factory.getButton(new Move(0, 0, MoveTypes.MOVE, Direction.LEFT)));
        arrowTable.add(factory.getButton(new Move(0, 0, MoveTypes.MOVE, Direction.DOWN)));
        arrowTable.add(factory.getButton(new Move(0, 0, MoveTypes.MOVE, Direction.RIGHT)));

        mainTable.addActor(actionsTable);

        mainTable.row();
        mainTable.addActor(arrowTable);
        arrowTable.debugAll();
    }
}