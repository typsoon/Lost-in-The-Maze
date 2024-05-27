package com.bksgames.game.views.updates;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.bksgames.game.common.updates.UpdateIDs;
import com.bksgames.game.common.updates.Update;
import com.bksgames.game.viewmodels.PlayerViewModel;
import com.bksgames.game.views.updates.updaters.*;

import java.util.HashMap;
import java.util.Map;

public class UpdateProcessor {
    final Map<UpdateIDs, Updater> updaterChooser = new HashMap<>();

    TiledMap map;
    TextureAtlas atlas;

    public void process(Update update) {
        updaterChooser.get(update.getID()).process(update);
    }

    public UpdateProcessor(TiledMap map, TextureAtlas atlas, PlayerViewModel playerViewModel) {
        this.map = map;
        this.atlas = atlas;

        updaterChooser.put(UpdateIDs.TILE_UPDATE, new TileUpdateUpdater(map, atlas, playerViewModel));
        updaterChooser.put(UpdateIDs.ENTITY_UPDATE, new EntityUpdateUpdater(map, atlas, playerViewModel));
        updaterChooser.put(UpdateIDs.LASER_UPDATE, new LaserUpdateHandler(map, atlas, playerViewModel));
        updaterChooser.put(UpdateIDs.SWORD_UPDATE, new SwordUpdateHandler(map, atlas, playerViewModel));
        updaterChooser.put(UpdateIDs.ENTITY_STATE_UPDATE, new EntityStateUpdater(map, atlas, playerViewModel));
    }
}
