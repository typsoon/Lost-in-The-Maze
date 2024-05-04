package com.bksgames.game.views;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bksgames.game.LostInTheMaze;

public class GameScreen implements Screen {

    final LostInTheMaze game;

    private final PlayerView playerView;
    private final MapObject map = new RectangleMapObject();
    private TextureAtlas atlas;
    private Skin skin;

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

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

    GameScreen(final LostInTheMaze game, PlayerView playerView) {
        this.playerView = playerView;
        this.game = game;
    }
}
