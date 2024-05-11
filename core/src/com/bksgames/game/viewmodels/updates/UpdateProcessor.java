package com.bksgames.game.viewmodels.updates;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.bksgames.game.enums.UpdateIDs;
import com.bksgames.game.updateData.Update;

import java.util.HashMap;
import java.util.Map;

public class UpdateProcessor {
    Map<UpdateIDs, Updater<? extends Update>> updaterChooser = new HashMap<>();

    TiledMap map;
    TextureAtlas atlas;

    public void process(Update update) {
        updaterChooser.get(update.getID()).process(update);
    }

    public UpdateProcessor(TiledMap map, TextureAtlas atlas) {
        this.map = map;
        this.atlas = atlas;

        updaterChooser.put(UpdateIDs.TILE_UPDATE, new TileUpdateUpdater(map, atlas));
    }
}
