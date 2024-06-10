package com.bksgames.game.views.gameScreen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.*;
import com.bksgames.game.LostInTheMaze;
import com.bksgames.game.services.PlayerService;
import com.bksgames.game.viewmodels.PlayerViewModel;
import com.bksgames.game.viewmodels.SimpleViewModel;
import com.bksgames.game.views.updates.UpdateProcessor;
import com.bksgames.game.viewmodels.ViewFrameObserver;
import com.bksgames.game.views.gameScreen.legalMovesHandling.LegalMoves;

public class GameScreen extends ScreenAdapter {

    final LostInTheMaze game;
    private final OrthographicCamera gameCamera;

    private final Viewport hudViewport;

    private final PlayerService playerService;

    private final TiledMap map;
    private final MapRenderer mapRenderer;

//    private Skin skin;

    private TextureAtlas boardAtlas;
    private TextureAtlas actionButtonsAtlas;

    private UpdateProcessor updateProcessor;

    private final ScreenMover screenMover;

    private LegalMoves legalMoves;

    private final PlayerViewModel playerViewModel;

    private final ViewFrameObserver viewLaserHandler;
    private final ViewFrameObserver viewSwordHandler;

    EndTheTurnStage endTurnStage;
    TextureAtlas atlas;

    //    Tiles are squares - tileSize is its width

    public GameScreen(final LostInTheMaze game, PlayerService playerService) {
        this.playerService = playerService;
        this.game = game;

        map = MazeMapFactory.produce();

        gameCamera = new OrthographicCamera();
        gameCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        mapRenderer = new OrthogonalTiledMapRenderer(map);

        hudViewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        hudViewport = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        hudViewport = new ScreenViewport(new OrthographicCamera());
//        hudViewport = new ScalingViewport(Scaling.stretch, 640, 480);
//        hudViewport = new ScalingViewport(800, 480, new OrthographicCamera());
//        hudViewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
//        hudViewport = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), hudCamera);
//        hudViewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());

//        playerViewModel = new SimpleViewModel((TiledMapTileLayer) map.getLayers().get("minions"));

        TiledMapTileLayer laserLayer = (TiledMapTileLayer) map.getLayers().get("laser");
        TiledMapTileLayer swordLayer = (TiledMapTileLayer) map.getLayers().get("sword");
        viewLaserHandler = new SimpleLaserHandler(laserLayer);
        viewSwordHandler = new SwordFrameHandler(swordLayer);
        playerViewModel = new SimpleViewModel(viewLaserHandler, viewSwordHandler);

        screenMover = new ScreenMover(gameCamera, playerViewModel);
    }

    @Override
    public void show() {
        hudViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

//        TODO: make it so the buttons are positioned and scaled correctly when the LegalMoves mainTable becomes visible
        hudViewport.setWorldSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        boardAtlas = new TextureAtlas(Gdx.files.internal("Board.atlas"));
        actionButtonsAtlas = new TextureAtlas(Gdx.files.internal("ActionButtons.atlas"));

        updateProcessor = new UpdateProcessor(map, boardAtlas, playerViewModel);

        legalMoves = new LegalMoves(actionButtonsAtlas, hudViewport, gameCamera, playerViewModel, playerService);

        atlas = new TextureAtlas(Gdx.files.internal("MainMenu.atlas"));
        endTurnStage = new EndTheTurnStage(atlas, game, hudViewport, playerService);

        MinionClickReceiver minionClickReceiver = new MinionClickReceiver(gameCamera, legalMoves, playerViewModel);

        InputMultiplexer inputMultiplexer = new InputMultiplexer(legalMoves, endTurnStage, screenMover, minionClickReceiver);

        gameCamera.position.set( MazeMapFactory.tilePixelSize* MazeMapFactory.maxBoardHeight, MazeMapFactory.tilePixelSize* MazeMapFactory.maxBoardWidth, 0);
        gameCamera.update();

        Gdx.input.setInputProcessor(inputMultiplexer);

        if (playerService.getWinner() != null)
            game.endGame(playerService.getWinner());
    }

    @Override
    public void render(float delta) {

        screenMover.update(delta);

        while (playerService.hasUpdates()) {
            updateProcessor.process(playerService.getUpdate());

            if (playerService.getWinner() != null)
                game.endGame(playerService.getWinner());
        }

        ScreenUtils.clear(0,0 , 0, 1);

        mapRenderer.setView(gameCamera);
        mapRenderer.render();

        if (viewLaserHandler.framePassed())
            return;

        viewSwordHandler.framePassed();

        legalMoves.act(delta);
        legalMoves.draw();

        endTurnStage.act(delta);
        endTurnStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameCamera.viewportWidth = width;
        gameCamera.viewportHeight = height;

        legalMoves.getViewport().update(width, height, false);
        hudViewport.update(width, height, false);
//        hudViewport.setWorldSize(width, height);
    }

    @Override
    public void dispose() {
        boardAtlas.dispose();
        legalMoves.dispose();
        actionButtonsAtlas.dispose();
    }
}
