package com.bksgames.game.views.gameScreen.legalMovesHandling;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.common.moves.Move;
import com.bksgames.game.common.moves.ActionToken;
import com.bksgames.game.common.utils.Direction;
import com.bksgames.game.services.PlayerService;
import com.bksgames.game.viewmodels.PlayerViewModel;
import com.bksgames.game.viewmodels.moves.IncompleteMove;
import com.bksgames.game.views.gameScreen.MazeMapFactory;
import com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons.ActionButtonFactory;
import com.bksgames.game.views.gameScreen.legalMovesHandling.actionButtons.Resizable;

import java.util.*;

public class LegalMoves extends Stage {
    private final Table mainTable;
    //    private TextureAtlas atlas;
    Map<IncompleteMove, Actor> moveToButtonMapping = new HashMap<>();

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
        act(0);
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

//        TODO: TO JEST ŁATKA - TRZEBA TO USUNĄĆ !!!!!!!!
        currentLegalMoves.add(new IncompleteMove(ActionToken.MIRROR, Direction.RIGHT));
        currentLegalMoves.add(new IncompleteMove(ActionToken.MIRROR, Direction.LEFT));
//

        for (Actor actor : moveToButtonMapping.values()) {
            actor.setVisible(false);
        }

        for (IncompleteMove incompleteMove : currentLegalMoves) {
            if (!moveToButtonMapping.containsKey(incompleteMove))
                throw new IllegalStateException("No such legal move" + incompleteMove);

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

//        TODO: remove magic values from the code below!!!
//        float multiplier = Math.max(gameCamera.zoom, 1);
        final float multiplier = Math.max(gameCamera.zoom, 0);
//        float multiplier = gameCamera.zoom;

//        arrowTable.setSize(MainTableFactory.arrowTableWidth * multiplier, MainTableFactory.arrowTableHeight * multiplier);
        mainTable.setSize(Gdx.graphics.getWidth()*multiplier, MainTableFactory.mainTableHeight*multiplier);
        mainTable.setPosition(worldCoordinates.x, worldCoordinates.y);
//        mainTable.setPosition(worldCoordinates.x + MainTableFactory.arrowButtonSize*multiplier*1.5f,
//                worldCoordinates.y + MainTableFactory.arrowButtonSize*multiplier*0.3f, Align.right);

        final float screenScalingWidth = Gdx.graphics.getWidth()/800f;
        final float screenScalingHeight = Gdx.graphics.getHeight()/480f;

        float newArrowButtonSize = MainTableFactory.arrowButtonSize * multiplier * screenScalingHeight;
//        float newButtonSize = MainTableFactory.arrowButtonSize * gameCamera.zoom;
        for (Cell<?> cell : arrowTable.getCells()) {
            cell.size(newArrowButtonSize, newArrowButtonSize);
        }

        float newActionButtonSize = MainTableFactory.actionButtonSize * multiplier * screenScalingHeight;
        for (Cell<?> cell : actionsTable.getCells()) {
            cell.size(newActionButtonSize, newActionButtonSize);
            if (cell.getActor() instanceof Resizable resizable)
                resizable.resize(multiplier);
        }

        actionsTable.getBackground().setMinWidth(MainTableFactory.actionsMenuWidth*multiplier*screenScalingWidth);
        actionsTable.getBackground().setMinHeight(MainTableFactory.actionsMenuHeight*multiplier*screenScalingHeight);
//        setSize(MainTableFactory.actionsMenuWidth * multiplier, MainTableFactory.actionsMenuHeight*multiplier);

        arrowTable.invalidate();
        arrowTable.layout();

//        TODO: think about whether this sends obsolete queries
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

        currentLegalMoves.clear();

        return playerService.sendMove(new Move(minionPosition, incompleteMove.type(), incompleteMove.direction()));
    }

    public LegalMoves(TextureAtlas atlas, Camera gameCamera, PlayerViewModel playerViewModel, PlayerService playerService) {
//        Stage
        super(new ScreenViewport(gameCamera));
        super.getViewport().setCamera(gameCamera);

        this.playerService = playerService;
        this.playerViewModel = playerViewModel;

//        super.getRoot().setColor(0,0,0,0.5f);
        super.getRoot().setColor(0,0,0,0.75f);
//        super.getRoot().setColor(0,0,0,0.9f);
//        super.setViewport(new ScreenViewport(gameCamera));
        super.getBatch().setProjectionMatrix(gameCamera.combined);

//        super.setDebugAll(true);

        ActionButtonFactory factory = new ActionButtonFactory(atlas, moveToButtonMapping);
        arrowTable = new Table();
        actionsTable = new Table();
        mainTable = MainTableFactory.produce(arrowTable, actionsTable, factory, atlas);
        this.addActor(mainTable);

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
//            if (event instanceof ChangeListener.ChangeEvent changeEvent) {
//                Gdx.app.log("ChangeEvent", changeEvent.toString());
//            }

            if (event instanceof ChosenMove chosenMove) {
                Gdx.app.log("Move has been chosen", String.valueOf(chosenMove.getIncompleteMove()));

                sendMove(chosenMove.getIncompleteMove());

                return true;
            }

            return false;
        });
    }
}