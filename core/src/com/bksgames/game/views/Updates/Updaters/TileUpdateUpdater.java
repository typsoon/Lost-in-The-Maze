package com.bksgames.game.views.Updates.Updaters;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.bksgames.game.views.Updates.UpdateKinds.TileUpdate;
import com.bksgames.game.views.Updates.Updater;

public class TileUpdateUpdater extends Updater<TileUpdate> {
    @Override
    public void process(TileUpdate tileUpdate) {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("tunnels");

//        layer.setCell(tileUpdate.getRelativeX(), tileUpdate.getRelativeY(), );
    }

    TileUpdateUpdater(TiledMap map, TextureAtlas atlas) {
        super(map, atlas);
    }
}
