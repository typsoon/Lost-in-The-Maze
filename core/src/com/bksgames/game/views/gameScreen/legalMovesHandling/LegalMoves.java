package com.bksgames.game.views.gameScreen.legalMovesHandling;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.services.PlayerService;
import com.bksgames.game.viewmodels.PlayerViewModel;
import com.bksgames.game.viewmodels.moves.IncompleteMove;
import com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons.ActionButtonFactory;

import java.util.*;

public class LegalMoves extends Stage {
    private final Table mainTable;
    //    private TextureAtlas atlas;
    Map<IncompleteMove, Actor> moveToButtonMapping = new HashMap<>();

//    TODO: remove minionMoveListener class
    private final PlayerService playerService;
    private final PlayerViewModel playerViewModel;
    int activeMinionId = -1;

    private final Table arrowTable;
    private final Table actionsTable;

    public void displayLegalMoves(int minionId) {
        activeMinionId = minionId;

        Point minionLocation = playerViewModel.getMinionPos(minionId);
        updateLegalMoves();

        Camera camera = getCamera();

//        camera.position.x = (minionLocation.x + MazeMapFactory.maxBoardWidth) * MazeMapFactory.tilePixelSize;
//        camera.position.y = (minionLocation.y + MazeMapFactory.maxBoardHeight) * MazeMapFactory.tilePixelSize;
//        camera.update();

        activateLegalMoves();
    }

    private void updateLegalMoves() {
        if (activeMinionId == -1) {
            throw new IllegalStateException("No active minion");
        }

        Point minionLocation = playerViewModel.getMinionPos(activeMinionId);
        Collection<Move> moves = playerService.getLegalMoves(minionLocation);

        if (moves == null)
            throw new IllegalStateException("legal moves are null");

        Collection<IncompleteMove> incompleteMoves = new ArrayList<>();
        moves.forEach(move -> incompleteMoves.add(new IncompleteMove(move.type(), move.direction())));

        for (Actor actor : moveToButtonMapping.values()) {
            actor.setVisible(false);
        }

        for (IncompleteMove incompleteMove : incompleteMoves) {
            moveToButtonMapping.get(incompleteMove).setVisible(true);
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (!isActive()) {
            return false;
        }

        boolean result = super.touchDown(screenX, screenY, pointer, button);
//
        if (result)
            updateLegalMoves();
        else deactivateLegalMoves();

        return false;
//        return false;
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
        activeMinionId = -1;
    }

    public void activateLegalMoves() {
        if (activeMinionId == -1) {
            throw new IllegalStateException("No active minion");
        }

        mainTable.setVisible(true);
    }

    public boolean isActive() {return mainTable.isVisible();}

    public boolean sendMove(IncompleteMove incompleteMove) {
        if (activeMinionId == -1) {
            throw new IllegalStateException("No active minion");
        }

        Point minionPosition = playerViewModel.getMinionPos(activeMinionId);

        return playerService.sendMove(new Move(minionPosition, incompleteMove.type(), incompleteMove.direction()));
    }

    public LegalMoves(TextureAtlas atlas, Camera gameCamera, PlayerViewModel playerViewModel, PlayerService playerService) {
//        Stage
        super(new ScreenViewport(gameCamera));
        super.getViewport().setCamera(gameCamera);

        this.playerService = playerService;
        this.playerViewModel = playerViewModel;

        super.getRoot().setColor(0,0,0,1);
//        super.setViewport(new ScreenViewport(gameCamera));
        super.getBatch().setProjectionMatrix(gameCamera.combined);

//        super.setDebugAll(true);

        ActionButtonFactory factory = new ActionButtonFactory(atlas, moveToButtonMapping);
        arrowTable = new Table();
        actionsTable = new Table();
        mainTable = MainTableFactory.produce(arrowTable, actionsTable, factory);
        this.addActor(mainTable);

//        super.act();
//        super.draw();

        this.addCaptureListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (!isActive()) {
                    return false;
                }

                return false;
            }

//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                if (!isActive()) {
//                    return false;
//                }
//
//                boolean result = arrowTable.notify(event, true);
//
//                if (result)
//                    updateLegalMoves();
//                else deactivateLegalMoves();
//
//                return result;
//            }
        });

        this.addListener(event -> {
            if (event instanceof ChosenMove chosenMove) {
                sendMove(chosenMove.getIncompleteMove());
                return true;
            }

            return false;
        });
    }
}