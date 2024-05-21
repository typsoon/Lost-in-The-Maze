package com.bksgames.game.views.gameScreen.legalMovesHandling;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.globalClasses.Move;
import com.bksgames.game.services.PlayerService;
import com.bksgames.game.viewmodels.PlayerViewModel;
import com.bksgames.game.viewmodels.moves.IncompleteMove;
import com.bksgames.game.views.gameScreen.MazeMapFactory;
import com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons.ActionButtonFactory;

import java.util.*;

public class LegalMoves extends Stage {
    private final Table mainTable;
    //    private TextureAtlas atlas;
    Map<IncompleteMove, Actor> moveToButtonMapping = new HashMap<>();

    //    TODO: remove minionMoveListener class
    private final PlayerService playerService;
    private final PlayerViewModel playerViewModel;

    private final Table arrowTable;
    private final Table actionsTable;

    int activeMinionId = -1;
    Collection<IncompleteMove> currentLegalMoves = new ArrayList<>();

    public void displayLegalMoves(int minionId) {
        activeMinionId = minionId;

        Point minionLocation = playerViewModel.getMinionPos(minionId);
        updateLegalMoves();

        Camera camera = getCamera();

        camera.position.x = (minionLocation.x + MazeMapFactory.maxBoardWidth) * MazeMapFactory.tilePixelSize;
        camera.position.y = (minionLocation.y + MazeMapFactory.maxBoardHeight) * MazeMapFactory.tilePixelSize;
        camera.update();

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

        currentLegalMoves = new ArrayList<>();
        moves.forEach(move -> currentLegalMoves.add(new IncompleteMove(move.type(), move.direction())));

        for (Actor actor : moveToButtonMapping.values()) {
            actor.setVisible(false);
        }

        for (IncompleteMove incompleteMove : currentLegalMoves) {
            moveToButtonMapping.get(incompleteMove).setVisible(true);
        }
    }


    @Override
    public void act(float deltaTime) {

        if (!isActive())
            return;

        OrthographicCamera gameCamera = (OrthographicCamera) getCamera();
        Vector3 screenCoordinates = new Vector3(0, Gdx.graphics.getHeight(), 0);
        Vector3 worldCoordinates = gameCamera.unproject(screenCoordinates);

//        Gdx.app.log("INFO", String.valueOf(gameCamera.viewportWidth));
//        Gdx.app.log("BottomLeftCorner", String.valueOf(worldCoordinates));
//        TODO: change this to render correctly
        mainTable.setPosition(worldCoordinates.x + 250, worldCoordinates.y, Align.right);

//        TODO: BIG BIG BIG BIG TODO - this sends a lot of obsolete queries to gameManager
        if (currentLegalMoves == null || currentLegalMoves.isEmpty()) {
            updateLegalMoves();
        }

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

        currentLegalMoves = null;

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

        mainTable.setPosition(400, 0);
//        super.act();
//        super.draw();

        this.addCaptureListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
//                Consume all arrow moves when the stage is Active
                if (!isActive()) {
                    return false;
                }

                if (keycode == Input.Keys.ESCAPE) {
                    deactivateLegalMoves();
                    return true;
                }

                arrowTable.notify(event, true);

                return List.of(Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.DOWN, Input.Keys.UP).contains(keycode);

//                consume all events that come in when the stage is Active
            }
        });

        this.addListener(event -> {
            if (event instanceof ChosenMove chosenMove) {
                sendMove(chosenMove.getIncompleteMove());

                updateLegalMoves();
                updateLegalMoves();return true;
            }

            return false;
        });
    }
}