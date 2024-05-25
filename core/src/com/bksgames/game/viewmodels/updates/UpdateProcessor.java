package com.bksgames.game.viewmodels.updates;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.bksgames.game.globalClasses.enums.UpdateIDs;
import com.bksgames.game.globalClasses.Update;
import com.bksgames.game.viewmodels.PlayerViewModel;
import com.bksgames.game.viewmodels.updates.updaters.LaserUpdateHandler;
import com.bksgames.game.viewmodels.updates.updaters.MinionUpdateUpdater;
import com.bksgames.game.viewmodels.updates.updaters.TileUpdateUpdater;
import com.bksgames.game.viewmodels.updates.updaters.Updater;

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
        updaterChooser.put(UpdateIDs.MINION_UPDATE, new MinionUpdateUpdater(map, atlas, playerViewModel));
        updaterChooser.put(UpdateIDs.LASER_UPDATE, new LaserUpdateHandler(map, atlas, playerViewModel));
    }
}
