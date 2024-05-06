package com.bksgames.game.views.Updates;

import com.badlogic.gdx.maps.tiled.TiledMap;

import java.util.HashMap;
import java.util.Map;

public class UpdateProcessor {
    Map<String, Updater> updaterChooser = new HashMap<String, Updater>();

    TiledMap map;

    public UpdateProcessor(TiledMap map) {
        this.map = map;
    }

    public void process(Update update) {
        updaterChooser.get(update.getKey()).process(update);
    }

    {

    }
}
