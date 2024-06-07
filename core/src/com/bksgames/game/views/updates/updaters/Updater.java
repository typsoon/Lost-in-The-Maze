package com.bksgames.game.views.updates.updaters;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.bksgames.game.common.updates.Update;
import com.bksgames.game.viewmodels.PlayerViewModel;

public abstract class Updater<T> {
    protected final TiledMap map;
    protected final TextureAtlas atlas;
    protected final PlayerViewModel playerViewModel;

    public abstract void process(T update);

    protected Updater(TiledMap map, TextureAtlas atlas, PlayerViewModel playerViewModel) {
        this.map = map;
        this.atlas = atlas;
        this.playerViewModel = playerViewModel;
    }
}
