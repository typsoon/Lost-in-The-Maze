package com.bksgames.game.views.Updates;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public abstract class Updater<T extends Update> {
    protected final TiledMap map;
    protected final TextureAtlas atlas;
    protected final Skin skin;

    public abstract void process(T update);

    protected Updater(TiledMap map, TextureAtlas atlas) {
        this.map = map;
        this.atlas = atlas;

        skin = new Skin(atlas);
    }
}
