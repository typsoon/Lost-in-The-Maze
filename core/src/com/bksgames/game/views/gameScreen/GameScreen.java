package com.bksgames.game.views.gameScreen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bksgames.game.LostInTheMaze;
import com.bksgames.game.core.updates.SimpleLaserUpdate;
import com.bksgames.game.core.utils.Point;
import com.bksgames.game.globalClasses.enums.Direction;
import com.bksgames.game.services.PlayerService;
import com.bksgames.game.viewmodels.PlayerViewModel;
import com.bksgames.game.viewmodels.SimpleViewModel;
import com.bksgames.game.viewmodels.updates.UpdateProcessor;
import com.bksgames.game.views.gameScreen.laserHandling.ViewLaserHandler;
import com.bksgames.game.views.gameScreen.laserHandling.SimpleLaserHandler;
import com.bksgames.game.views.gameScreen.legalMovesHandling.LegalMoves;

public class GameScreen implements Screen {

    final LostInTheMaze game;
    private final OrthographicCamera gameCamera;

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

    private final ViewLaserHandler viewLaserHandler;

    //    Tiles are squares - tileSize is its width

    public GameScreen(final LostInTheMaze game, PlayerService playerService) {
        this.playerService = playerService;
        this.game = game;

        map = MazeMapFactory.produce();

        gameCamera = new OrthographicCamera();
        gameCamera.setToOrtho(false, 800, 480);

        mapRenderer = new OrthogonalTiledMapRenderer(map);

//        playerViewModel = new SimpleViewModel((TiledMapTileLayer) map.getLayers().get("minions"));

        TiledMapTileLayer laserLayer = (TiledMapTileLayer) map.getLayers().get("laser");
        viewLaserHandler = new SimpleLaserHandler(laserLayer);
        playerViewModel = new SimpleViewModel(viewLaserHandler);

        screenMover = new ScreenMover(gameCamera, playerViewModel);
    }

    @Override
    public void show() {
        boardAtlas = new TextureAtlas(Gdx.files.internal("Board.atlas"));
//        skin = new Skin(boardAtlas);
        actionButtonsAtlas = new TextureAtlas(Gdx.files.internal("ActionButtons.atlas"));

        updateProcessor = new UpdateProcessor(map, boardAtlas, playerViewModel);

        legalMoves = new LegalMoves(actionButtonsAtlas, gameCamera, playerViewModel, playerService);

        MinionClickReceiver minionClickReceiver = new MinionClickReceiver(gameCamera, legalMoves, playerViewModel);

//        inputMultiplexer = new InputMultiplexer(screenMover, minionClickReceiver);
        InputMultiplexer inputMultiplexer = new InputMultiplexer(legalMoves, screenMover, minionClickReceiver);

        gameCamera.position.set( MazeMapFactory.tilePixelSize* MazeMapFactory.maxBoardHeight, MazeMapFactory.tilePixelSize* MazeMapFactory.maxBoardWidth, 0);
        gameCamera.update();

//        legalMoves.setViewport(new ScreenViewport(gameCamera));

        Gdx.input.setInputProcessor(inputMultiplexer);

        playerService.pushUpdate(new SimpleLaserUpdate(Direction.RIGHT, null, new Point(-2,-2)));
        playerService.pushUpdate(new SimpleLaserUpdate(Direction.RIGHT, Direction.UP, new Point(-1,-2)));
        playerService.pushUpdate(new SimpleLaserUpdate(Direction.UP, null, new Point(-1,-1)));
        playerService.pushUpdate(new SimpleLaserUpdate(Direction.UP, Direction.LEFT, new Point(-1,0)));
    }

    @Override
    public void render(float delta) {
        screenMover.update(delta);

        while (playerService.hasUpdates()) {
            updateProcessor.process(playerService.getUpdate());
        }

        ScreenUtils.clear(0,0 , 0, 0);

        legalMoves.act(delta);//, gameCamera);

//        legalMoves.getViewport().update((int) gameCamera.position.x, (int) gameCamera.position.y, true);

        mapRenderer.setView(gameCamera);
        mapRenderer.render();

        viewLaserHandler.framePassed();
        legalMoves.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameCamera.viewportWidth = width;
        gameCamera.viewportHeight = height;

        legalMoves.getViewport().update(width, height, false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        boardAtlas.dispose();
        legalMoves.dispose();
        actionButtonsAtlas.dispose();
    }
}
