package com.bksgames.game.views.gameScreen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bksgames.game.LostInTheMaze;
import com.bksgames.game.services.PlayerService;
import com.bksgames.game.viewmodels.moves.SimpleMoveMaker;
import com.bksgames.game.viewmodels.updates.UpdateProcessor;

public class GameScreen implements Screen {

    final LostInTheMaze game;
    private final OrthographicCamera gameCamera;

    private final PlayerService playerService;
    private final TiledMap map = new TiledMap();
    private TextureAtlas atlas;
    private Skin skin;

    private final ScreenMover screenMover;
    private final MapRenderer mapRenderer;

    private UpdateProcessor updateProcessor;
    private final InputMultiplexer inputMultiplexer;
    private final LegalMovesHandler legalMovesDisplayer;

    //    Tiles are squares - tileSize is its width
    final static public int tilePixelSize = 50;
    final static public int maxBoardWidth = 300;
    final static public int maxBoardHeight = 300;

    public GameScreen(final LostInTheMaze game, PlayerService playerService) {
        this.playerService = playerService;
        this.game = game;

        gameCamera = new OrthographicCamera();
        gameCamera.setToOrtho(false, 800, 480);
        gameCamera.position.set(tilePixelSize* maxBoardHeight, tilePixelSize* maxBoardWidth, 0);
        gameCamera.update();

        mapRenderer = new OrthogonalTiledMapRenderer(map);

        TiledMapTileLayer minions = new TiledMapTileLayer(3* maxBoardWidth, 3* maxBoardHeight, tilePixelSize, tilePixelSize);
        minions.setName("minions");

        TiledMapTileLayer tunnels = new TiledMapTileLayer(3* maxBoardWidth, 3* maxBoardHeight, tilePixelSize, tilePixelSize);
        tunnels.setName("tunnels");

        TiledMapTileLayer wallsAndNexuses = new TiledMapTileLayer(3* maxBoardWidth, 3* maxBoardHeight, tilePixelSize, tilePixelSize);
        wallsAndNexuses.setName("wallsAndNexuses");

        map.getLayers().add(tunnels);
        map.getLayers().add(wallsAndNexuses);
        map.getLayers().add(minions);

        screenMover = new ScreenMover(gameCamera);
        MoveSender moveSender = new MoveSender(playerService, map, minions, gameCamera);
        inputMultiplexer = new InputMultiplexer(screenMover, moveSender);
        legalMovesDisplayer = new LegalMovesHandler(inputMultiplexer, new SimpleMoveMaker());
    }

    @Override
    public void show() {
        atlas = new TextureAtlas(Gdx.files.internal("Board.atlas"));
        skin = new Skin(atlas);

        updateProcessor = new UpdateProcessor(map, atlas);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        screenMover.update(delta);

        while (playerService.hasUpdates()) {
            updateProcessor.process(playerService.getUpdate());
        }

        ScreenUtils.clear(0,0 , 0, 0);

        mapRenderer.setView(gameCamera);
        mapRenderer.render();
    }

    @Override
    public void resize(int width, int height) {
//        gameCamera.viewportWidth = width;
//        gameCamera.viewportHeight = height;
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
        atlas.dispose();
        skin.dispose();
    }
}
