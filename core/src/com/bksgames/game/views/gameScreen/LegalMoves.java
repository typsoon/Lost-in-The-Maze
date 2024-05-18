package com.bksgames.game.views.gameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.viewmodels.moves.MinionMoveListener;
import com.bksgames.game.views.gameScreen.actionButtons.ActionButtonFactory;

import java.util.*;

public class LegalMoves extends Stage {
    private final Table mainTable;
    //    private TextureAtlas atlas;
//    Map<IncompleteMove, Actor> mapping;
    private final MinionMoveListener minionMoveListener;

    private final Table arrowTable;
    private final Table actionsTable;

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

//        if (!result)
        deactivateLegalMoves();

        for (Actor actor : arrowTable.getChildren()) {
            if (!actor.isVisible())
                continue;

            for (EventListener listener : actor.getCaptureListeners()) {
                if (!(listener instanceof InputListener inputListener)) {
                    throw new IllegalStateException("Illegal state");
                }

                if(inputListener.keyDown(new InputEvent(), keyCode))
                    return true;
            }
        }

        return result;
    }

    @Override
    public void act(float deltaTime) {

        Camera gameCamera = getCamera();
//        Vector3 screenCoordinates = new Vector3(Gdx.graphics.getWidth() , Gdx.graphics.getHeight(), 0);
        Vector3 screenCoordinates = new Vector3(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0);
        Vector3 worldCoordinates = gameCamera.unproject(screenCoordinates);

//        TODO: change this to render correctly
        mainTable.setPosition(worldCoordinates.x - gameCamera.viewportWidth, worldCoordinates.y);

        super.act(deltaTime);
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

        ActionButtonFactory factory = new ActionButtonFactory(minionMoveListener, atlas);
        arrowTable = new Table();
        actionsTable = new Table();
        mainTable = MainTableFactory.produce(arrowTable, actionsTable, factory);
        this.addActor(mainTable);

        super.act();
        super.draw();
    }
}