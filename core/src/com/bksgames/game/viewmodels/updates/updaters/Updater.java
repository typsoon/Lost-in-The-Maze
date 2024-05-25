package com.bksgames.game.viewmodels.updates.updaters;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.bksgames.game.common.Update;
import com.bksgames.game.viewmodels.PlayerViewModel;

public abstract class Updater {
    protected final TiledMap map;
    protected final TextureAtlas atlas;
    protected final Skin skin;
    protected final PlayerViewModel playerViewModel;

    public abstract void process(Update update);

    protected Updater(TiledMap map, TextureAtlas atlas, PlayerViewModel playerViewModel) {
        this.map = map;
        this.atlas = atlas;
        this.playerViewModel = playerViewModel;

        skin = new Skin(atlas);
    }
}
