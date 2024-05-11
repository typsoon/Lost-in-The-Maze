package com.bksgames.game.viewmodels.updates;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.bksgames.game.updateData.TileUpdate;
import com.bksgames.game.updateData.Update;

public class TileUpdateUpdater extends Updater<TileUpdate> {
    @Override
    public void process(Update tileUpdate) {


        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("tunnels");

//        layer.setCell(tileUpdate.getRelativeX(), tileUpdate.getRelativeY(), );
    }

    TileUpdateUpdater(TiledMap map, TextureAtlas atlas) {
        super(map, atlas);
    }
}
