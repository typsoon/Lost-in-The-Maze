package com.bksgames.game.views;

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
import com.bksgames.game.Testing;
import com.bksgames.game.services.PlayerService;
import com.bksgames.game.updateData.Update;
import com.bksgames.game.viewmodels.updates.UpdateProcessor;

public class GameScreen implements Screen {

    final LostInTheMaze game;

    private final OrthographicCamera gameCamera;

    private final PlayerService playerService;
    private final TiledMap map = new TiledMap();
    private TextureAtlas atlas;
    private Skin skin;
    private InputProcessor inputProcessor;
    private final MapRenderer mapRenderer;



    private UpdateProcessor updateProcessor;

    //    Tiles are squares - tileSize is its width
    final static public int tilePixelSize = 50;
    final static public int maxBoardWidth = 300;
    final static public int maxBoardLength = 300;

    public GameScreen(final LostInTheMaze game, PlayerService playerService) {
        this.playerService = playerService;
        this.game = game;

        gameCamera = new OrthographicCamera();
        gameCamera.setToOrtho(false, 800, 480);
        gameCamera.position.set(tilePixelSize*maxBoardWidth, tilePixelSize*maxBoardLength, 0);
        gameCamera.update();



        inputProcessor = new InputAdapter() {
            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return super.touchDragged(screenX, screenY, pointer);
            }
        };

        Gdx.input.setInputProcessor(inputProcessor);

        mapRenderer = new OrthogonalTiledMapRenderer(map);

        TiledMapTileLayer tunnels = new TiledMapTileLayer(3*maxBoardLength, 3*maxBoardWidth, tilePixelSize, tilePixelSize);
        tunnels.setName("tunnels");

        TiledMapTileLayer wallsAndNexuses = new TiledMapTileLayer(3*maxBoardLength, 3*maxBoardWidth, tilePixelSize, tilePixelSize);
        wallsAndNexuses.setName("wallsAndNexuses");

        TiledMapTileLayer minions = new TiledMapTileLayer(3*maxBoardLength, 3*maxBoardWidth, tilePixelSize, tilePixelSize);
        minions.setName("minions");

        map.getLayers().add(tunnels);
        map.getLayers().add(wallsAndNexuses);
        map.getLayers().add(minions);
    }

    @Override
    public void show() {
        atlas = new TextureAtlas(Gdx.files.internal("Board.atlas"));
        skin = new Skin(atlas);

        updateProcessor = new UpdateProcessor(map, atlas);
//        TODO: TEST THIS
//        Testing.dummyUpdater(playerService);
    }

    @Override
    public void render(float delta) {
        handleInput();

        while (playerService.hasUpdates()) {
            processUpdate(playerService.getUpdate());
            System.out.println("processed update");
        }

        ScreenUtils.clear(0,0 , 0, 0);
//        ScreenUtils.clear(1,1, 1, 0);

        mapRenderer.setView(gameCamera);
        mapRenderer.render();
//        System.out.println("rendered");
    }

    @Override
    public void resize(int width, int height) {

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

    void processUpdate(Update update) {
        updateProcessor.process(update);
    }

    public float cameraSpeed = 5f;
    private void handleInput() {

        // Move the camera based on user input
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            gameCamera.position.x -= cameraSpeed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            gameCamera.position.x += cameraSpeed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            gameCamera.position.y += cameraSpeed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            gameCamera.position.y -= cameraSpeed;
        }

        // Update the camera's view
        gameCamera.update();
    }
}
