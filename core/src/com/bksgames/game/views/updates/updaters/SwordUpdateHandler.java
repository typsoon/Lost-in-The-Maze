package com.bksgames.game.views.updates.updaters;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.bksgames.game.common.updates.SwordUpdate;
import com.bksgames.game.viewmodels.PlayerViewModel;

public class SwordUpdateHandler extends Updater<SwordUpdate> {
    @Override
    public void process(SwordUpdate swordUpdate) {
    }

    public SwordUpdateHandler(TiledMap map, TextureAtlas atlas, PlayerViewModel playerViewModel) {
        super(map, atlas, playerViewModel);
    }
}
